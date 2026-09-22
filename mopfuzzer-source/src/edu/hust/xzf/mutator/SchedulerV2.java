// Modified for EvoFuzz-AE: instrumentation, scheduling, execution accounting, and portable examples.
package edu.hust.xzf.mutator;

import edu.hust.xzf.entity.Pair;
import edu.hust.xzf.entity.Triple;
import edu.hust.xzf.mutator.config.Configuration;
import edu.hust.xzf.mutator.utils.FS;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.HashSet;

public class SchedulerV2 {
    
    public static String folderPath = null;
    public static String lockPath = null;
    public static String scoreDataPath = null;
    public static String pathdb = null;
    public final static String HEAD_MUT = "nil";
    public final static String HEAD_SEED = "nil";
    public final static String SCORE_PLACEHOLDER = "none";
    private static final Random random = new Random();

    private static final double ALPHA = 0.75;
    private static final double TRANS_DECAY = 0.5;
    private static final double C_EXPLORE = 2.0;
    private static final double FAILED_PENALTY = 0.9;
    private static final double TIMEDOUT_PENALTY = 0.5;
    private static final double INFINIE_WEIGHT_RATE = 1000.0;
    private static final double FAKE_INFINIE_WEIGHT_RATE = 0.0001;

    private static ScoreData oldScoreData = null;

    public static void initialize() {
        try {
            folderPath = String.valueOf(Configuration.TEMP_FILES_PATH);
            lockPath = folderPath + "scheduler.lock";
            scoreDataPath = folderPath + "score_data";
            pathdb = folderPath + "pathdb";
            if (!FS.isExist(folderPath)) {
                FS.ensureDirExists(folderPath);
            }
            try {
                RandomAccessFile file = new RandomAccessFile(lockPath, "rw");
                FileChannel channel = file.getChannel();
                FileLock lock = channel.lock();
                if (lock != null) {
                    try {                        
                        if (!FS.isExist(pathdb)) {
                            FS.createNewFile(pathdb);
                        }
                        if (!FS.isExist(scoreDataPath)) {
                            FS.createNewFile(scoreDataPath);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        lock.release();
                    }
                }
                channel.close();
                file.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Scheduler.logSth("V2Scheduler initialized with folderPath: " + folderPath);
        } catch (Exception e) {
            e.printStackTrace();
            folderPath = null;
            Scheduler.logSth("V2Scheduler failed to initialize jarPath due to URISyntaxException.");
        }
    }

    private static ScoreData getOldScoreData() {
        if (oldScoreData == null) {
            oldScoreData = getAllScore();
        }
        return oldScoreData;
    }

    public static int selectSeed(List<String> seeds) {
        if (seeds == null || seeds.isEmpty()) {
            return -1; // No seeds available
        }

        ScoreData oldScore = getOldScoreData();
        Integer ret = null;

        try {
            Scheduler.logSth("V2Scheduler select a seed from " + seeds.size() + " candidates.");
            // Build Map: Seed Name -> Index
            Map<String, Integer> seedIndexMap = new HashMap<>();
            for (int i = 0; i < seeds.size(); i++) {
                seedIndexMap.put(seeds.get(i), i);
            }

            // Select Top 50% seeds based on seed-mut score
            List<String> candidateSeeds = new ArrayList<>();
            List<Pair<Double, Integer>> seedScores = new ArrayList<>();
            for (Map.Entry<Pair<String, String>, Triple<Double, Integer, String>> entry: oldScore.scoreData.entrySet()) {
                Pair<String, String> labelPair = entry.getKey();
                Triple<Double, Integer, String> dataPair = entry.getValue();
                String seed = labelPair.getFirst();
                String mut = labelPair.getSecond();
                if (!mut.equals(SCORE_PLACEHOLDER) && seeds.contains(seed)) {
                    candidateSeeds.add(seed);
                    seedScores.add(new Pair<>(dataPair.getFirst(), dataPair.getSecond()));
                }
            }
            
            List<Integer> topIndices = null;
            if (seedScores.size() <= 0) {
                Scheduler.logSth("V2Scheduler no seed-mut score data available for candidate seeds, fallback to random selection.");
                topIndices = new ArrayList<>();
            } else if (seedScores.size() <= 2) {
                topIndices = selectTop(seedScores, 1);
            } else {
                topIndices = selectTop(seedScores, seedScores.size() / 2);
            }

            // Final selection
            List<String> finalCandis = new ArrayList<>();
            List<Pair<Double, Integer>> finalScores = new ArrayList<>();
            for (int idx: topIndices) {
                finalCandis.add(candidateSeeds.get(idx));
                finalScores.add(seedScores.get(idx));
            }
            for (String seed: seeds) {
                if (oldScore.scoreData.containsKey(new Pair<>(seed, SCORE_PLACEHOLDER))) {
                    Triple<Double, Integer, String> dataPair = oldScore.scoreData.get(new Pair<>(seed, SCORE_PLACEHOLDER));
                    finalCandis.add(seed);
                    finalScores.add(new Pair<>(dataPair.getFirst(), dataPair.getSecond()));
                } else {
                    finalCandis.add(seed);
                    finalScores.add(new Pair<>(0.0, 0)); // Default score for unseen seed
                }
            }
            Integer finalTopIndices = selectTop(finalScores);
            Scheduler.logSth("V2Scheduler select a seed of idx: " + finalTopIndices);

            // Return
            if (finalTopIndices == null || !seedIndexMap.containsKey(finalCandis.get(finalTopIndices))) {
                ret = null;
            } else {
                ret = seedIndexMap.get(finalCandis.get(finalTopIndices));
            }
        } catch (Exception e) {
            Scheduler.logSth("V2Scheduler failed to select seed based on scores due to exception: " + e.toString());
            e.printStackTrace();
            ret = null;
        }

        if (ret == null) {
            Scheduler.logSth("V2Scheduler fallback to random seed selection.");
            ret = random.nextInt(seeds.size()); // Fallback to random selection in case of any exceptions
        }
        Scheduler.logSth("V2Scheduler selected seed idx: " + ret + " seed: " + seeds.get(ret));
        return ret;
    }
    
    public static List<Integer> selectMut(String seed, List<String> mutators) {
        return selectMut(seed, mutators, 2);
    }

    public static Integer selectMut(String seed, List<String> mutators, String prevmut) {
        if (mutators == null || mutators.isEmpty()) {
            return null; // No mutators available
        }
        if (folderPath == null) {            
            return random.nextInt(mutators.size());
        }

        try {
            // Build Map: Mut Name -> Index
            Map<String, Integer> mutIndexMap = new HashMap<>();
            for (int i = 0; i < mutators.size(); i++) {
                mutIndexMap.put(mutators.get(i), i);
            }

            ScoreData oldScore = getOldScoreData();
            String prevMut = prevmut;
            Integer ret = null;
            List<List<Pair<Double, Integer>>> mutScores = new ArrayList<>();
            for (String mut: mutators) {
                List<Pair<Double, Integer>> scores = new ArrayList<>();
                if (oldScore.scoreData.containsKey(new Pair<>(SCORE_PLACEHOLDER, mut))) {
                    Triple<Double, Integer, String> dataPair = oldScore.scoreData.get(new Pair<>(SCORE_PLACEHOLDER, mut));
                    scores.add(new Pair<>(dataPair.getFirst(), dataPair.getSecond()));
                } else {
                    scores.add(new Pair<>(0.0, 0)); // Default score for unseen gb-mut pairs
                }
                if (oldScore.scoreData.containsKey(new Pair<>(seed, mut))) {
                    Triple<Double, Integer, String> dataPair = oldScore.scoreData.get(new Pair<>(seed, mut));
                    scores.add(new Pair<>(dataPair.getFirst(), dataPair.getSecond()));
                } else {
                    scores.add(new Pair<>(0.0, 0)); // Default score for unseen seed-mut pairs
                }
                if (oldScore.scoreData.containsKey(new Pair<>(prevMut, mut))) {
                    Triple<Double, Integer, String> dataPair = oldScore.scoreData.get(new Pair<>(prevMut, mut));
                    scores.add(new Pair<>(dataPair.getFirst(), dataPair.getSecond()));
                } else {
                    scores.add(new Pair<>(0.0, 0)); // Default score for unseen prevMut-mut pairs
                }
                mutScores.add(scores);
            }
            ret = selectTopKWithW(mutScores);
            if (ret == null) {
                throw new Exception("Failed to select mutator based on scores");
            }
            Scheduler.logSth("V2Scheduler selected mutator: " + mutators.get(ret) + " for seed " + seed + " with prev mut " + prevmut);
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // No cleanup needed in this implementation
        }
        Scheduler.logSth("V2Scheduler fallback to random mutator selection.");
        return random.nextInt(mutators.size());
    }
    
    public static List<Integer> selectMut(String seed, List<String> mutators, int num) {
        if (mutators == null || mutators.isEmpty() || num <= 0) {
            return new ArrayList<>(); // No mutators available
        }
        if (folderPath == null) {
            List<Integer> ret = new ArrayList<>();
            for (int i = 0; i < num; i++) {
                ret.add(random.nextInt(mutators.size())); // Fallback to random selection if unable to determine jar path
            }
            return ret;
        }

        try {
            // Build Map: Seed Name -> Index
            Map<String, Integer> mutIndexMap = new HashMap<>();
            for (int i = 0; i < mutators.size(); i++) {
                mutIndexMap.put(mutators.get(i), i);
            }

            ScoreData oldScore = getOldScoreData();
            String prevMut = HEAD_MUT;
            List<Integer> selectMuts = new ArrayList<>();
            for (int i = 0; i < num; i++) {
                List<List<Pair<Double, Integer>>> mutScores = new ArrayList<>();
                for (String mut: mutators) {
                    List<Pair<Double, Integer>> scores = new ArrayList<>();
                    if (oldScore.scoreData.containsKey(new Pair<>(SCORE_PLACEHOLDER, mut))) {
                        Triple<Double, Integer, String> dataPair = oldScore.scoreData.get(new Pair<>(SCORE_PLACEHOLDER, mut));
                        scores.add(new Pair<>(dataPair.getFirst(), dataPair.getSecond()));
                    } else {
                        scores.add(new Pair<>(0.0, 0)); // Default score for unseen gb-mut pairs
                    }
                    if (oldScore.scoreData.containsKey(new Pair<>(seed, mut))) {
                        Triple<Double, Integer, String> dataPair = oldScore.scoreData.get(new Pair<>(seed, mut));
                        scores.add(new Pair<>(dataPair.getFirst(), dataPair.getSecond()));
                    } else {
                        scores.add(new Pair<>(0.0, 0)); // Default score for unseen seed-mut pairs
                    }
                    if (oldScore.scoreData.containsKey(new Pair<>(prevMut, mut))) {
                        Triple<Double, Integer, String> dataPair = oldScore.scoreData.get(new Pair<>(prevMut, mut));
                        scores.add(new Pair<>(dataPair.getFirst(), dataPair.getSecond()));
                    } else {
                        scores.add(new Pair<>(0.0, 0)); // Default score for unseen prevMut-mut pairs
                    }
                    mutScores.add(scores);
                }
                Integer selIdx = selectTopKWithW(mutScores);
                if (selIdx == null) {
                    throw new Exception("Failed to select mutator based on scores");
                }
                selectMuts.add(selIdx);
                Scheduler.logSth("V2Scheduler selected mutator idx: " + selIdx + " mut: " + mutators.get(selIdx) + " for seed " + seed);
                prevMut = mutators.get(selIdx);
            }
            return selectMuts;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // No cleanup needed in this implementation
        }
        Scheduler.logSth("V2Scheduler fallback to random mutator selection.");
        List<Integer> ret = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            ret.add(random.nextInt(mutators.size())); // Fallback to random selection if unable to determine jar path
        }
        return ret;
    }

    private static void updateScore(String currfile, String seed, List<String> mutators, double score) {
        if (lockPath == null) {
            return; // Unable to determine lock path
        }
        if (currfile == null) {
            currfile = "null";
        }
        try {
            RandomAccessFile file = new RandomAccessFile(lockPath, "rw");
            FileChannel channel = file.getChannel();
            FileLock lock = channel.lock();
            if (lock != null) {
                try {
                    ScoreData data = new ScoreData(scoreDataPath);
                    
                    // update gb-mut score and seed-mut score
                    for (String mut: mutators) {
                        data.updateScore(seed, mut, score);
                        data.updateScore(SCORE_PLACEHOLDER, mut, score);
                    }

                    // update mut-mut score
                    String prevMut = HEAD_MUT;
                    for (int i = 0; i < mutators.size(); i++) {
                        String currMut = mutators.get(i);
                        data.updateScore(prevMut, currMut, score);
                        prevMut = currMut;
                    }

                    // update gb-seed
                    String currSeed = seed;
                    double currAlpha = ALPHA;
                    while (currSeed != null && currAlpha >= 0.0001 && !currSeed.equals(HEAD_SEED)) {
                        data.updateScore(currSeed, SCORE_PLACEHOLDER, score, currAlpha);
                        currSeed = data.getParent(currSeed);
                        currAlpha = currAlpha * TRANS_DECAY;
                    }

                    // update curr-file score
                    if (!currfile.equals("null")) {
                        data.updateScore(currfile, SCORE_PLACEHOLDER, score, seed);
                    }
                    
                    // WB
                    data.wirteBack();
                    Scheduler.logSth("V2Scheduler updated scores for seed: " + seed + " mutators: " + mutators.get(0) + ", " + mutators.get(1) + " with score: " + score);
                
                } catch (Exception e) {
                    Scheduler.logSth("V2Scheduler updated scores failed for seed: " + seed + " mutators: " + mutators.get(0) + ", " + mutators.get(1) + " with score: " + score);
                    e.printStackTrace();
                } finally {
                    lock.release();
                }
            }
            channel.close();
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return; // Placeholder for actual score retrieval logic
    }

    private static ScoreData getAllScore() {
        if (lockPath == null) {
            return null; // Unable to determine lock path
        }
        try {
            RandomAccessFile file = new RandomAccessFile(lockPath, "rw");
            FileChannel channel = file.getChannel();
            FileLock lock = channel.lock();
            ScoreData ret = null;
            if (lock != null) {
                try {
                    ret = new ScoreData(scoreDataPath);
                } catch (Exception e) {
                    Scheduler.logSth("V2Scheduler failed to load score data due to exception: " + e.toString());
                    ret = null;
                } finally {
                    lock.release();
                }
            }
            channel.close();
            file.close();
            return ret;
        } catch (Exception e) {
            Scheduler.logSth("V2Scheduler failed to load score data due to exception: " + e.toString());
        }
        return null; // Placeholder for actual score retrieval logic
    }

    private static Integer selectTopKWithW(List<List<Pair<Double, Integer>>> orgScores) {
        List<Double> w = new ArrayList<>();
        w.add(1.0 / 7.0);
        w.add(2.0 / 7.0);
        w.add(4.0 / 7.0);
        return selectTopKWithW(orgScores, w);
    }
    
    private static Integer selectTopKWithW(List<List<Pair<Double, Integer>>> orgScores, List<Double> w) {
        try {
            if (orgScores == null || orgScores.isEmpty()) {
                return null; // No scores available or invalid k
            }
            if (orgScores.size() == 1) {
                return 0; // Return all indices if scores are less than or equal to k
            }
            
            // Count total tries
            int totalTries = 0;
            boolean hasUsing = false;
            for (int i = 0; i < orgScores.size(); i++) {
                int currTries = 0;
                for (Pair<Double, Integer> sc: orgScores.get(i)) {
                    int cnt = sc.getSecond();
                    if (cnt >= 0) {
                        currTries += cnt;
                    } else {
                        hasUsing = true;
                    }
                }
                totalTries += currTries;
            }

            // If there are no tries yet, randomly select one
            if (totalTries == 0 && !hasUsing) {
                return random.nextInt(orgScores.size()); // Random Choose One if no tries yet
            }

            // If there are somes cases has score, find the max one
            double maxWeight = -1000000.0;
            if (totalTries > 0) {
                for (int idx = 0; idx < orgScores.size(); idx++) {
                    List<Pair<Double, Integer>> scores = orgScores.get(idx);
                    for (int jdx = 0; jdx < scores.size(); jdx++) {
                        Pair<Double, Integer> score = scores.get(jdx);
                        if (score.getSecond() > 0) {
                            double exploit = score.getFirst() * w.get(jdx);
                            double explore = C_EXPLORE * Math.sqrt(Math.log(totalTries) / score.getSecond());
                            if (exploit + explore > maxWeight) {
                                maxWeight = exploit + explore;
                            }
                        }
                    }
                }
            } else {
                // totalTries == 0 && hasUsing == true
                maxWeight = 1.0; // If no tries yet, set maxWeight to 1.0 to allow selection of zero-try candidates
            }

            // Calculate weights for each candidate and select based on weighted random
            List<Double> weights = new ArrayList<>();
            for (int idx = 0; idx < orgScores.size(); idx++) {
                List<Pair<Double, Integer>> scores = orgScores.get(idx);
                double currWeight = 0;
                for (int jdx = 0; jdx < scores.size(); jdx++) {
                    Pair<Double, Integer> score = scores.get(jdx);
                    if (score.getSecond() > 0) {
                        double exploit = score.getFirst();
                        double explore = C_EXPLORE * Math.sqrt(Math.log(totalTries) / score.getSecond());
                        currWeight += (exploit + explore) * w.get(jdx);
                    } else if (score.getSecond() == 0) {
                        currWeight = maxWeight * INFINIE_WEIGHT_RATE * w.get(jdx);
                    } else {
                        currWeight = FAKE_INFINIE_WEIGHT_RATE * w.get(jdx);
                    }
                }
                weights.add(currWeight);
            }

            return weightedRandom(weights);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // Return empty list in case of any exceptions
    }
    
    private static Integer selectTop(List<Pair<Double, Integer>> orgScores) {
        List<Integer> ret = selectTop(orgScores, 1);
        if (ret.isEmpty()) {
            return null; // No valid scores available
        }
        return ret.get(0);
    }
    
    private static List<Integer> selectTop(List<Pair<Double, Integer>> orgScores, int k) {
        try {
            if (orgScores == null || orgScores.isEmpty() || k <= 0) {
                Scheduler.logSth("V2Scheduler selectTop invalid orgScores or k: " + k);
                return new ArrayList<>(); // No scores available or invalid k
            }
            if (orgScores.size() <= k) {
                List<Integer> allIndices = new ArrayList<>();
                for (int i = 0; i < orgScores.size(); i++) {
                    allIndices.add(i);
                }
                return allIndices; // Return all indices if scores are less than or equal to k
            }

            List<Integer> ret = new ArrayList<>();

            // Count total tries
            int totalTries = 0;
            boolean hasUsing = false;
            for (int i = 0; i < orgScores.size(); i++) {
                int cnt = orgScores.get(i).getSecond();
                if (cnt >= 0) {
                    totalTries += cnt;
                } else {
                    hasUsing = true;
                }
            }

            if (totalTries == 0 && !hasUsing) {
                Scheduler.logSth("V2Scheduler selectTop random select one as all scores are zero and no using data");
                // Random Choose One
                for (int i = 0; i < k; i++) {
                    int idx = random.nextInt(orgScores.size());
                    ret.add(idx);
                    if (ret.size() >= k) {
                        return ret; // Return if we have already selected k candidates
                    }
                }
            }

            // If there are somes cases has score, find the max one
            double maxWeight = -1000000.0;
            if (totalTries > 0) {    
                for (int idx = 0; idx < orgScores.size(); idx++) {
                    Pair<Double, Integer> score = orgScores.get(idx);
                    if (score.getSecond() > 0) {
                        double exploit = score.getFirst();
                        double explore = C_EXPLORE * Math.sqrt(Math.log(totalTries) / score.getSecond());
                        if (exploit + explore > maxWeight) {
                            maxWeight = exploit + explore;
                        }
                    }
                }
            } else {
                // totalTries == 0 && hasUsing == true
                maxWeight = 1.0; // If no tries yet, set maxWeight to 1.0 to allow selection of zero-try candidates
                Scheduler.logSth("V2Scheduler set maxWeight to: " + 
                    maxWeight * INFINIE_WEIGHT_RATE);
            }

            // Calculate weights for each candidate and select based on weighted random
            List<Double> weights = new ArrayList<>();
            for (int idx = 0; idx < orgScores.size(); idx++) {
                Pair<Double, Integer> score = orgScores.get(idx);
                if (score.getSecond() > 0) {
                    double exploit = score.getFirst();
                    double explore = C_EXPLORE * Math.sqrt(Math.log(totalTries) / score.getSecond());
                    weights.add(exploit + explore);
                } else if (score.getSecond() == 0) {
                    // Assign a weight higher than maxWeight to ensure that candidates with zero tries are selected first
                    weights.add(maxWeight * INFINIE_WEIGHT_RATE);
                } else {
                    weights.add(FAKE_INFINIE_WEIGHT_RATE);
                }
            }

            while (ret.size() < k && !weights.isEmpty()) {
                int selectedIdx = weightedRandom(weights);
                ret.add(selectedIdx);
            }        
            return ret;
        } catch (Exception e) {
            Scheduler.logSth("V2Scheduler failed to select top candidates based on scores due to exception: " + e.toString());
        }
        Scheduler.logSth("V2Scheduler fallback to random selection of candidates.");
        List<Integer> ret = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            int idx = random.nextInt(orgScores.size());
            ret.add(idx);
            if (ret.size() >= k) {
                return ret; // Return if we have already selected k candidates
            }
        }
        return new ArrayList<>(); // Return empty list in case of any exceptions
    }
    
    private static int weightedRandom(List<Double> weights) {
        double totalWeight = 0.0;
        for (double weight : weights) {
            totalWeight += weight;
        }
        double randomValue = random.nextDouble() * totalWeight;
        double cumulativeWeight = 0.0;
        for (int i = 0; i < weights.size(); i++) {
            cumulativeWeight += weights.get(i);
            if (randomValue < cumulativeWeight) {
                Scheduler.logSth("V2Scheduler selcted index: " + i + " with weight: " + weights.get(i) + " out of total weight: " + totalWeight);
                return i;
            }
        }
        return weights.size() - 1; // Fallback in case of rounding issues
    }

    private static class ScoreData {
        
        public Map<Pair<String, String>, Triple<Double, Integer, String>> scoreData;
        private String dataPath;
        private static final String score_dilimiter = "|";
        private static final String label_dilimiter = ":"; 

        public ScoreData(String filePath) {
            try {
                dataPath = filePath;
                scoreData = new HashMap<>();
                if (!FS.isExist(filePath)) {
                    // create one
                    Scheduler.logSth("V2Scheduler score data file does not exist, creating new one at: " + filePath);
                    FS.createNewFile(filePath);
                } else {
                    List<String> lines = FS.readLines(filePath);
                    Scheduler.logSth("V2Scheduler loaded org score file with " + lines.size());
                    for (String line: lines) {
                        line = line.strip();
                        if (!line.isEmpty()) {
                            List<String> parts = new ArrayList<>();
                            int startIdx = 0;
                            for (int i = 0; i < line.length(); i++) {
                                if (line.charAt(i) == score_dilimiter.charAt(0)) {
                                    parts.add(line.substring(startIdx, i));
                                    startIdx = i + 1;
                                }
                            }
                            if (startIdx < line.length()) {
                                parts.add(line.substring(startIdx));
                            }
                            if (parts.size() == 4) {
                                String label = parts.get(0).strip();
                                String data = parts.get(1).strip();
                                String num = parts.get(2).strip();
                                String parentSeed = parts.get(3).strip();
                                String[] labelParts = label.split(label_dilimiter);
                                if (labelParts.length == 2) {
                                    String seed = labelParts[0].strip();
                                    String mut = labelParts[1].strip();
                                    try {
                                        double score = Double.parseDouble(data);
                                        int count = Integer.parseInt(num);
                                        scoreData.put(new Pair<>(seed, mut), new Triple<>(score, count, parentSeed));
                                    } catch (NumberFormatException e) {
                                        // Skip invalid score or count format
                                    }
                                }
                            }
                        }
                    }
                    Scheduler.logSth("V2Scheduler loaded score data with " + scoreData.size());
                }
            } catch (Exception e) {
                e.printStackTrace();
                Scheduler.logSth("V2Scheduler failed to load score data from file: " + filePath + " due to exception: " + e.toString());
                scoreData = null;
                dataPath = null;
            }
        }
    
        public void wirteBack() {
            if (dataPath == null || scoreData == null) {
                return; // Unable to write back due to invalid data path or score data
            }
            try {
                StringBuilder sb = new StringBuilder();
                for (Map.Entry<Pair<String, String>, Triple<Double, Integer, String>> entry: scoreData.entrySet()) {
                    Pair<String, String> labelPair = entry.getKey();
                    Triple<Double, Integer, String> dataPair = entry.getValue();
                    String label = labelPair.getFirst() + label_dilimiter + labelPair.getSecond();
                    String data = String.valueOf(dataPair.getFirst());
                    String num = String.valueOf(dataPair.getSecond());
                    String parentSeed = dataPair.getThird();
                    sb.append(label).append(score_dilimiter).append(data).append(score_dilimiter)
                        .append(num).append(score_dilimiter).append(parentSeed).append("\n");
                }
                FS.writeString(dataPath, sb.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void updateScore(String a, String b, double score) {
            updateScore(a, b, score, ALPHA);
        }
        
        public void updateScore(String a, String b, double score, double alpha) {
            if (scoreData == null) {
                return; // Unable to update score due to invalid score data
            }
            Pair<String, String> labelPair = new Pair<>(a, b);
            double newScore = score;
            if (scoreData.containsKey(labelPair)) {
                Triple<Double, Integer, String> dataPair = scoreData.get(labelPair);
                double oldScore = dataPair.getFirst();
                int oldCount = Math.max(dataPair.getSecond(), 0);
                String parentSeed = dataPair.getThird();
                newScore = oldScore * (1 - alpha) + score * alpha;
                scoreData.put(labelPair, new Triple<>(newScore, oldCount + 1, parentSeed));
            } else {
                scoreData.put(labelPair, new Triple<>(score, 1, HEAD_SEED));
            }
            Scheduler.logSth("V2Scheduler updated score for label: " + labelPair + " to: " + newScore);
        }

        public void updateScore(String a, String b, double score, String parentSeed) {
            updateScore(a, b, score, parentSeed, ALPHA);
        }
        
        public void updateScore(String a, String b, double score, String parentSeed, double alpha) {
            if (scoreData == null) {
                return; // Unable to update score due to invalid score data
            }
            Pair<String, String> labelPair = new Pair<>(a, b);
            double newScore = score;
            if (scoreData.containsKey(labelPair)) {
                Triple<Double, Integer, String> dataPair = scoreData.get(labelPair);
                double oldScore = dataPair.getFirst();
                int oldCount = Math.max(dataPair.getSecond(), 0);
                newScore = oldScore * (1 - alpha) + score * alpha;
                scoreData.put(labelPair, new Triple<>(newScore, oldCount + 1, parentSeed));
            } else {
                scoreData.put(labelPair, new Triple<>(score, 1, parentSeed));
            }
            Scheduler.logSth("V2Scheduler updated score for label: " + labelPair + " to: " + newScore);
        }

        public String getParent(String seed) {
            if (scoreData == null) {
                return null; // Unable to get parent due to invalid score data
            }
            Pair<String, String> labelPair = new Pair<>(seed, SCORE_PLACEHOLDER);
            if (scoreData.containsKey(labelPair)) {
                Triple<Double, Integer, String> dataPair = scoreData.get(labelPair);
                return dataPair.getThird();
            }
            return null; // Return null if parent seed is not found
        }
    
    }

    public static void scoreTestCase(String score_dir, String score_file, String seed, List<String> mutators, String curr_file, int exit_code) {
        
        Set<String> newLines = null;
        int newCnt = 0;
        double totalRate = 1.0;
        if (exit_code == 1) {
            totalRate = FAILED_PENALTY;
        } else if (exit_code != 0) {
            totalRate = TIMEDOUT_PENALTY;
        }
        if (score_dir != null && score_file != null && folderPath != null && lockPath != null) {
            try {
                RandomAccessFile file = new RandomAccessFile(lockPath, "rw");
                FileChannel channel = file.getChannel();
                FileLock lock = channel.lock();
                if (lock != null) {
                    try {
                        Set<String> alreadyLines = new HashSet<>(FS.readLines(pathdb));
                        newLines = stdFile2PathSet(score_file);
                        Scheduler.logSth("V2Scheduler alreadyLines: " + alreadyLines.size() + " newLines: " + newLines.size());
                        if (alreadyLines != null && newLines != null) {
                            for (String line: newLines) {
                                if (!alreadyLines.contains(line)) {
                                    alreadyLines.add(line);
                                    newCnt += 1;
                                }
                            }
                            FS.writeLines(pathdb, alreadyLines.stream().toList());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        newLines = null;
                        Scheduler.logSth("V2Scheduler failed (" + e.toString() + ") to process score file for test case: " + curr_file);
                    } finally {
                        lock.release();
                    }
                }
                channel.close();
                file.close();
                Scheduler.logSth("V2Scheduler has " + newCnt + " new paths for the test case: " + curr_file);
            } catch (Exception e) {
                e.printStackTrace();
                newLines = null;
                Scheduler.logSth("V2Scheduler failed (" + e.toString() + ") to acquire lock for scoring test case: " + curr_file);
            }
        }
        if (newLines == null) {
            updateScore(curr_file, seed, mutators, 1.0); // Assign a default score in case of failure to process new lines
            return;
        }
        pathSet2ScoreFle(newLines, score_dir + FS.getStem(curr_file) + ".score");
        seed = seed.replace("//", "/");
        Set<String> seedLines = scoreFile2PathSet(FS.getParentDirString(seed) + FS.getStem(seed) + ".score");
        if (seedLines == null) {
            seedLines = new HashSet<>();
        }
        Set<String> commonLines = new HashSet<>();
        for (String line: newLines) {
            if (seedLines.contains(line)) {
                commonLines.add(line);
            }
        }
        Scheduler.logSth("V2Scheduler found " + commonLines.size() + " common paths between seed: " + seed + " and current test case: " + curr_file);

        String cpDir = Configuration.orgProjectPath + "/new_seeds/" + String.valueOf(System.nanoTime());
        while (FS.isExist(cpDir)) {
            try {
                Thread.sleep(random.nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cpDir = Configuration.orgProjectPath + "/" + String.valueOf(System.nanoTime());
        }
        FS.ensureDirExists(cpDir);
        FS.cpFile(curr_file, cpDir);
        FS.cpFile(score_dir + FS.getStem(curr_file) + ".score", cpDir);
        curr_file = cpDir + "/" + FS.getFileName(curr_file);

        double score = 10.0;
        if (newCnt > 0) {
            score += Math.pow((1.0 + (newCnt / newLines.size())), 1.5);
        }
        if (newLines.size() > 0) {
            score += ((1 - (2 * commonLines.size()) / (seedLines.size() + newLines.size())) * 200);
        }
        score = totalRate * score;
        score = Math.max(score, 1.0); // Ensure a minimum score of 1.0 for any new test case
        updateScore(curr_file, seed, mutators, score);
        return;
    }

    private static void pathSet2ScoreFle(Set<String> pathSet, String filePath) {
        Map<String, Map<String, List<String>>> fileFuncMap = new HashMap<>();
        for (String line: pathSet) {
            String[] parts = line.split(":::");
            if (parts.length == 3) {
                String file = parts[0].strip();
                String func = parts[1].strip();
                String path = parts[2].strip();
                fileFuncMap.putIfAbsent(file, new HashMap<>());
                Map<String, List<String>> funcMap = fileFuncMap.get(file);
                funcMap.putIfAbsent(func, new ArrayList<>());
                funcMap.get(func).add(path);
            }
        }
        List<String> lines = new ArrayList<>();
        for (Map.Entry<String, Map<String, List<String>>> fileEntry: fileFuncMap.entrySet()) {
            String file = fileEntry.getKey();
            Map<String, List<String>> funcMap = fileEntry.getValue();
            lines.add("File:::: " + file);
            for (Map.Entry<String, List<String>> funcEntry: funcMap.entrySet()) {
                String func = funcEntry.getKey();
                List<String> paths = funcEntry.getValue();
                lines.add("Function:::: " + func);
                for (String path: paths) {
                    lines.add(path);
                }
            }
        }
        FS.writeLines(filePath, lines);
        return;
    }

    private static Set<String> stdFile2PathSet(String filePath) {
        List<String> newLines = FS.readLines(filePath);
        if (newLines == null) {
            return null; // Unable to read score data
        }
        Set<String> ret = new HashSet<>();
        String currFile = null;
        String currFunc = null;
        for (String line: newLines) {
            line = line.strip();
            if (line.startsWith("[RCOV] ")) {
                line = line.substring(7).strip();
                if (line.startsWith("File:::: ")) {
                    currFile = line.substring(9).strip();
                } else if (line.startsWith("Function:::: ")) {
                    currFunc = line.substring(13).strip();
                } else if (!line.contains("::::") && currFile != null && currFunc != null) {
                    String newLine = currFile + ":::" + currFunc + ":::" + line;
                    ret.add(newLine);                    
                }
            }
        }
        return ret;
    }

    private static Set<String> scoreFile2PathSet(String filePath) {
        List<String> newLines = FS.readLines(filePath);
        if (newLines == null) {
            return null; // Unable to read score data
        }
        Set<String> ret = new HashSet<>();
        String currFile = null;
        String currFunc = null;
        for (String line: newLines) {
            line = line.strip();
            if (line.startsWith("File:::: ")) {
                currFile = line.substring(9).strip();
            } else if (line.startsWith("Function:::: ")) {
                currFunc = line.substring(13).strip();
            } else if (!line.contains("::::") && currFile != null && currFunc != null) {
                String newLine = currFile + ":::" + currFunc + ":::" + line;
                ret.add(newLine);                    
            }
        }
        return ret;
    }

    public static void tryLockUsingSeed(String seedPath) {
        try {
            RandomAccessFile file = new RandomAccessFile(lockPath, "rw");
            FileChannel channel = file.getChannel();
            FileLock lock = channel.lock();
            if (lock != null) {
                try {
                    ScoreData sd = new ScoreData(scoreDataPath);
                    if (!sd.scoreData.containsKey(new Pair<>(seedPath, SCORE_PLACEHOLDER))) {
                        sd.scoreData.put(new Pair<>(seedPath, SCORE_PLACEHOLDER), new Triple<>(0.0, -1, HEAD_SEED));
                        sd.wirteBack();
                        Scheduler.logSth("V2Scheduler lock seed: " + seedPath);
                    } else {
                        Scheduler.logSth("V2Scheduler skip locking seed: " + seedPath + " as it is already exist in score data");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.release();
                }
            }
            channel.close();
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
