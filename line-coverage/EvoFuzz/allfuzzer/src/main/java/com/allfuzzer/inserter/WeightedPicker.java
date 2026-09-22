package com.allfuzzer.inserter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.allfuzzer.domain.MutatorUnit;
import com.allfuzzer.util.RandomChooser;

/**
 * Picker that selects MutatorUnit objects based on their priority using weighted random selection.
 * Higher priority values correspond to lower selection probabilities.
 */
public class WeightedPicker {

    // Store cumulative weights to enable binary search
    static class Item {
        long weightAccum; // cumulative weight (strictly increasing if each weight > 0)
        MutatorUnit object;
    }

    private int idx = -1;
    private int count = 0;
    private final Map<String, List<Item>> items;
    private Map<String, Integer> mutatorExprUsageCount;
    private Map<String, Integer> mutatorStmtUsageCount;

    public WeightedPicker(Map<String, List<MutatorUnit>> mutMap, boolean inOrder) {
        if (mutMap == null || mutMap.isEmpty()) {
            throw new IllegalArgumentException("List of MutatorUnit cannot be null or empty");
        }
        if (inOrder) {
            this.idx = 0;
        }
        double eps = 1e-9;   // Prevent division by zero
        double alpha = 2.0;  // Adjust skewness: >1 favors higher priority more

        this.items = new HashMap<>();
        this.mutatorStmtUsageCount = new HashMap<>();
        this.mutatorExprUsageCount = new HashMap<>();

        for (Map.Entry<String, List<MutatorUnit>> mu: mutMap.entrySet()) {
            String retType = mu.getKey();
            List<Item> targetList = new ArrayList<>();
            this.items.put(retType, targetList);
            long accum = 0L;
            for (MutatorUnit mut : mu.getValue()) {
                double p = mut.getPriority();
                if (p < 0 || Double.isNaN(p) || Double.isInfinite(p)) {
                    mut.setPriority(50);
                    p = 50;
                    // throw new IllegalArgumentException("priority must be a non-negative finite number");
                }
                double w = Math.pow(1.0 / (p + eps), alpha);

                // Scale to long for cumulative weights.
                // Note: extremely small weights may round to 0 and thus contribute no probability.
                long scaled = (long) (w * 1e6);
                accum += scaled;

                Item item = new Item();
                item.weightAccum = accum;
                item.object = mut;
                targetList.add(item);

                String currFile = mut.getSrcFilePath();

                if (!mutatorStmtUsageCount.containsKey(currFile)) {
                    mutatorStmtUsageCount.put(currFile, 0);
                    mutatorExprUsageCount.put(currFile, 0);
                }
            }
        }
    }

    /**
     * Check if there is any mutator for the given type.
     * @param targetType the target return type 
     * @return true if there is at least one mutator for the target type, false otherwise
     */
    public boolean hasMutator(String targetType) {
        List<Item> targetList = this.items.get(targetType);
        return targetList != null && !targetList.isEmpty();
    }

    /**
     * Select the next MutatorUnit for the given target type using weighted random selection.
     * @param targetType the target return type
     * @return the selected MutatorUnit, or null if no mutator is available for the target type
     *       The Boolean in the Pair indicates whether more mutators are available for selection.
     */
    public MutatorUnit nextMutator(String targetType) {
        List<Item> targetList = this.items.get(targetType);
        if (targetList == null || targetList.isEmpty()) {
            return null;
        }

        // If in-order selection is enabled, return the next mutator in sequence.
        if (this.idx >= 0 && targetType.equals("void")) {
            
            int startIdx = this.idx % targetList.size();
            String selectFile = targetList.get(startIdx).object.getSrcFilePath();
            
            List<Integer> candidateIdxs = new ArrayList<>();
            for (int i = 0; i < targetList.size(); i++) {
                String currFile = targetList.get(i).object.getSrcFilePath();
                if (currFile.equals(selectFile)) {
                    candidateIdxs.add(i);
                }
            }

            int retIdx = candidateIdxs.get(RandomChooser.nextInt(candidateIdxs.size()));
            MutatorUnit ret = targetList.get(retIdx).object;
            mutatorStmtUsageCount.put(selectFile, mutatorStmtUsageCount.get(selectFile) + 1);
            
            this.idx += candidateIdxs.size();
            this.count += 1;


            return ret;
        }

        long totalWeight = targetList.get(targetList.size() - 1).weightAccum;
        long r = (long) (RandomChooser.nextDouble() * totalWeight); // r in [0, totalWeight)

        // Binary search for the first item with cumulative weight > r (upper bound).
        int lo = 0;
        int hi = targetList.size() - 1;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            long cum = targetList.get(mid).weightAccum;
            if (r < cum) {
                // The target is in [lo, mid]
                hi = mid;
            } else {
                // The target is in (mid, hi]
                lo = mid + 1;
            }
        }
        MutatorUnit ret = targetList.get(lo).object;
        String currFile = ret.getSrcFilePath();
        if (targetType.equals("void")) {
            mutatorStmtUsageCount.put(currFile, mutatorStmtUsageCount.get(currFile) + 1);
            
            // Increase priority slightly to reduce future selection probability
            ret.setPriority((int)(ret.getPriority() * 1.1));
            for (int i = lo; i < targetList.size(); i++) {
                Item item = targetList.get(i);
                double p = item.object.getPriority();
                double w = Math.pow(1.0 / (p + 1e-9), 2.0);
                long scaled = (long) (w * 1e6);
                if (i == 0) {
                    item.weightAccum = scaled;
                } else {
                    item.weightAccum = targetList.get(i - 1).weightAccum + scaled;
                }
            }

            this.count += 1;
        }
        else {
            mutatorExprUsageCount.put(currFile, mutatorExprUsageCount.get(currFile) + 1);
        }
        return ret;
    }

    /**
     * Peek at the next MutatorUnit for the given target type without updating usage counts.
     * @param targetType the target return type
     * @return the selected MutatorUnit, or null if no mutator is available for the target type
     */
    public MutatorUnit peekNextMutator(String targetType) {
        List<Item> targetList = this.items.get(targetType);
        if (targetList == null || targetList.isEmpty()) {
            return null;
        }

        // If in-order selection is enabled, return the next mutator in sequence.
        if (this.idx >= 0 && targetType.equals("void")) {
            MutatorUnit ret = targetList.get(this.idx % targetList.size()).object;
            return ret;
        }

        long totalWeight = targetList.get(targetList.size() - 1).weightAccum;
        long r = (long) (RandomChooser.nextDouble() * totalWeight); // r in [0, totalWeight)

        // Binary search for the first item with cumulative weight > r (upper bound).
        int lo = 0;
        int hi = targetList.size() - 1;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            long cum = targetList.get(mid).weightAccum;
            if (r < cum) {
                // The target is in [lo, mid]
                hi = mid;
            } else {
                // The target is in (mid, hi]
                lo = mid + 1;
            }
        }
        return targetList.get(lo).object;
    }

    public boolean meetEnd(String targetType) {
        List<Item> targetList = this.items.get(targetType);
        if (targetList == null || targetList.isEmpty()) {
            return true;
        }
        if (this.idx >= 0 && targetType.equals("void")) {
            return this.idx >= targetList.size() && this.idx % targetList.size() == 0;
        }
        return false;
    }

    /**
     * Get the count of mutators available for the given target type.
     * @param targetType the target return type
     * @return the number of mutators available for the target type
     */
    public int getMutatorCount(String targetType) {
        List<Item> targetList = this.items.get(targetType);
        if (targetList == null) {
            return 0;
        }
        return targetList.size();
    }

    /**
     * Get the usage count of each mutator.
     * @return a map from mutator source file path to its usage count
     */
    public Map<String, Map<String, Integer>> getMutatorUsageCount() {
        Map<String, Map<String, Integer>> ret = new HashMap<>();
        ret.put("Stmt", mutatorStmtUsageCount);
        ret.put("Expr", mutatorExprUsageCount);
        return ret;
    }

    public int getCount() {
        return this.count;
    }

}
