package com.allfuzzer.preprocess;


import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

import com.allfuzzer.domain.MutatorUnit;
import com.allfuzzer.domain.MutatorUnit.MutType;
import com.allfuzzer.util.Logger;

/**
 * MutatorScanner traverses a given directory to find Java source files,
 * analyzes them using MutatorAnalyzer, and organizes the extracted MutatorUnit objects
 * by their return types.
 */
public class MutatorScanner {

    /**
     * Holds the result of scanning mutators, categorized into different maps
     * based on their types.
     */
    public static class ScanResult {
        public Map<String, List<MutatorUnit>> mutatorMap;
        public Map<String, List<MutatorUnit>> newMap;
        public Map<String, List<MutatorUnit>> initMap;
        public Map<String, List<MutatorUnit>> checkMap;

        public ScanResult() {
            this.mutatorMap = new HashMap<>();
            this.newMap = new HashMap<>();
            this.initMap = new HashMap<>();
            this.checkMap = new HashMap<>();
        }
    }
    
    /**
     * Scans the specified directory for Java source files, analyzes them,
     *  and returns a map of MutatorUnit lists categorized by their return types.
     * @param codeBricks Set of file paths to code bricks
     * @param getCodeBricksPriority List of priorities corresponding to each code brick
     * @return a map where the key is the return type and the value is a list of MutatorUnit objects
     */
    public static ScanResult scanMutators(List<String> codeBricks, List<Integer> getCodeBricksPriority) {

        // Map: ReturnType -> List of MutatorUnit
        ScanResult ret = new ScanResult();

        for (int idx = 0; idx < codeBricks.size(); idx++) {
            String mut_path = codeBricks.get(idx);
            int priority = getCodeBricksPriority.get(idx);
            List<MutatorUnit> muts = MutatorAnalyzer.analyzeMutator(mut_path, priority);
            String srcfile = String.valueOf(idx + 1) + "::" + mut_path;
            for (MutatorUnit mu : muts) {
                List<MutatorUnit> list = null;
                Map<String, List<MutatorUnit>> toput = null;
                mu.setSrcFilePath(srcfile);
                switch (mu.getMutType()) {
                    case MutType.FUNCTION:
                    case MutType.STATEMENT:
                        toput = ret.mutatorMap;
                        list = toput.get(mu.getRetType());
                        break;
                    case MutType.NEW:
                        toput = ret.newMap;
                        list = toput.get(mu.getRetType());
                        break;
                    case MutType.INIT:
                        toput = ret.initMap;
                        list = toput.get(mu.getRetType());
                        break;
                    case MutType.CHECK:
                        toput = ret.checkMap;
                        list = toput.get(mu.getRetType());
                        break;
                    default:
                        Logger.error("scanMutators: unknown mutator type: " + mu.getMutType().toString());
                        continue;
                }
                if (list == null) {
                    list = new ArrayList<>();
                    toput.put(mu.getRetType(), list);
                }
                list.add(mu);
            }
        }

        if (ret.mutatorMap.isEmpty()) {
            throw new RuntimeException("No valid mutators found in the specified code bricks.");
        }

        return ret;
    }

}