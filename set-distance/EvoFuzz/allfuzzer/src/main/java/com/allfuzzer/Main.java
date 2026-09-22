package com.allfuzzer;

import com.allfuzzer.config.Config;
import com.allfuzzer.preprocess.HostAnalyzer;
import com.allfuzzer.preprocess.MutatorScanner;
import com.allfuzzer.inserter.StmtInserter;
import com.allfuzzer.output.Writer;
import com.allfuzzer.spoon.SpoonFacade;
import com.allfuzzer.util.Logger;

public class Main {
    
    public static void main(String[] args) {
        try {
            // Preprocess the host code and mutators
            Config.loadConfig(args);
            SpoonFacade spoon = new SpoonFacade(Config.getSrcPath(), true, false);
            MutatorScanner.ScanResult muMap = MutatorScanner.scanMutators(Config.getCodeBricks(), Config.getCodeBricksPriority());
            HostAnalyzer.InsertionPoints insertionPoints = HostAnalyzer.analyzeHost(spoon, Config.isInsertInOnePoint());
            Logger.info("Host code and mutators preprocessed successfully.");

            // Perform random insertion of mutators
            StmtInserter.Result result = StmtInserter.insert(insertionPoints, muMap, 
                Config.getStmtCnt(), Config.isInsertInOrder(), Config.isInsertInOnePoint());
            Logger.info("Total mutators inserted: " + result.Count());

            // Write back the modified code
            Writer.writeBack(spoon, insertionPoints, result, Config.getOutPath(),
                Config.getDataOutput(), Config.getArraySize());
            Logger.info("Mutated code written to: " + Config.getOutPath());
        
        } catch (Exception e) {
            Logger.error(e);
            Logger.error("Fatal error occurred. Exiting.");
            System.exit(1);
        }

    }
}
