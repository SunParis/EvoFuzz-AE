package com.allfuzzer.output;

import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.allfuzzer.preprocess.HostAnalyzer;
import com.allfuzzer.domain.MutatorUnit;
import com.allfuzzer.inserter.StmtInserter;
import com.allfuzzer.spoon.SpoonFacade;
import com.allfuzzer.spoon.SpoonCode.*;
import com.allfuzzer.util.RandomChooser;
import com.allfuzzer.util.Logger;

/**
 * Writer class to handle writing back modified code with inserted mutators and variables.
 */
public class Writer {

    /**
     * Insert local variables into the method.
     * @param spoon SpoonFacade instance
     * @param method SpoonMethod instance
     * @param localVarInMethod Map of local variable types to their names in the method
     * @param data StmtInserter.Result instance containing required mutator units
     */
    private static void insertLocalVars(SpoonFacade spoon, SpoonMethod method,
        Map<String, Set<String>> localVarInMethod, StmtInserter.Result data
    ) {
        if (localVarInMethod == null || localVarInMethod.size() == 0) {
            return;
        }
        Map<String, String> newMems = new java.util.HashMap<>();
        Map<String, String> iniExprs = new java.util.HashMap<>();
        Map<String, String> checkStmts = new java.util.HashMap<>();
        
        // For each local variable type
        for (String type : localVarInMethod.keySet()) {
            
            // For each local variable name of `type`
            for (String varName : localVarInMethod.get(type)) {
                
                List<MutatorUnit> list = data.requiredAlloc.get(type);
                if (list != null && list.size() > 0) {
                    String new_mem = list.get(RandomChooser.nextInt(list.size())).getMutString();
                    Logger.debug("Local var `" + varName + "` new mem: \n" + new_mem);
                    newMems.put(varName, new_mem);
                }
                
                list = data.requiredInit.get(type);
                if (list != null && list.size() > 0) {
                    iniExprs.put(varName, list.get(RandomChooser.nextInt(list.size())).getMutString(varName));
                }

                list = data.requiredChecks.get(type);
                if (list != null && list.size() > 0) {
                    checkStmts.put(varName, list.get(RandomChooser.nextInt(list.size())).getMutString(varName));
                }
            }
        }
        spoon.insertLocalVars(method, localVarInMethod, newMems, iniExprs, checkStmts);
    }

    /**
     * Generate AllFuzzer specific strings for the output code.
     * @param data StmtInserter.Result instance containing required functions and variable info
     * @param arraySize array size configuration
     * @return List of strings to be added to the output code
     */
    private static List<String> allFuzzerStr(StmtInserter.Result data, int arraySize) {
        List<String> ret = new ArrayList<>();
        ret.add("public static int ARRAY_SIZE = " + arraySize + ";\n");
        for (Map.Entry<String, String> func : data.requiredFuncs.entrySet()) {
            ret.add(func.getValue() + "\n");
        }

        // gbvars: Name -> Type
        List<String> gbVarInit = new ArrayList<>();
        for (Map.Entry<String, Set<String>> exntries: data.gbVarMap.entrySet()) {
            String type = exntries.getKey();
            for (String gbname : exntries.getValue()) {
                String newMem = null;
                if (data.requiredAlloc.containsKey(type)) {
                    List<MutatorUnit> newList = data.requiredAlloc.get(type);
                    if (newList != null && newList.size() > 0) {
                        newMem = newList.get(RandomChooser.nextInt(newList.size())).getMutString();
                    }
                }
                if (data.requiredInit.containsKey(type)) {
                    List<MutatorUnit> initList = data.requiredInit.get(type);
                    if (initList != null && initList.size() > 0) {
                        String init = initList.get(RandomChooser.nextInt(initList.size())).getMutString(gbname);
                        Logger.debug("Global var `" + gbname + "` init: \n" + init);
                        gbVarInit.add(init);
                    }
                }

                if (newMem != null) {
                    ret.add(String.format("public static %s %s = %s;\n",
                        type, gbname, newMem));
                }
                else {
                    ret.add(String.format("public static %s %s;\n",
                        type, gbname));
                }
                
            }
        }
        if (gbVarInit.size() > 0) {
            ret.add("static {\n");
            for (String initStmt : gbVarInit) {
                ret.add(initStmt + "\n");
            }
            ret.add("}\n");
        }
        
        return ret;
    }

    /**
     * Generate required class strings for the output code.
     * @param data StmtInserter.Result instance containing required class info
     * @return List of class strings to be added to the output code
     */
    private static List<String> requiredClazStr(StmtInserter.Result data) {
        List<String> ret = new ArrayList<>();
        for (Map.Entry<String, String> clazz : data.requiredClazzs.entrySet()) {
            ret.add(clazz.getValue() + "\n");
        }
        return ret;
    }

    private static void writeJson(Map<String, Map<String, Integer>> data, String dataOutPath) {
        try {
            List<String> allLines = new ArrayList<>();
            allLines.add("{");
            for (Map.Entry<String, Map<String, Integer>> entry : data.entrySet()) {
                allLines.add("    \"" + entry.getKey() + "\": {");
                for (Map.Entry<String, Integer> subEntry : entry.getValue().entrySet()) {
                    allLines.add("        \"" + subEntry.getKey() + "\": " + subEntry.getValue() + ",");
                }
                if (allLines.size() > 2) {
                    // Remove the last comma
                    String lastLine = allLines.get(allLines.size() - 1);
                    allLines.set(allLines.size() - 1, lastLine.substring(0, lastLine.length() - 1));
                }
                allLines.add("    },");
            }
            if (allLines.size() > 2) {
                // Remove the last comma
                String lastLine = allLines.get(allLines.size() - 1);
                allLines.set(allLines.size() - 1, lastLine.substring(0, lastLine.length() - 1));
            }
            allLines.add("}");
            Files.write(Paths.get(dataOutPath), allLines);
        } catch (Exception e) {
            Logger.error("Failed to write mutator usage data to " + dataOutPath);
        }
    }
    
    /**
     * Write back the modified code to the output path.
     * @param spoon SpoonFacade instance
     * @param insertionPoints insertion points in the host code
     * @param data StmtInserter.Result instance containing inserted mutators and variable info
     * @param outPath output path for the modified code
     * @param dataOutPath output path for additional data (not used in this method)
     * @param arraySize array size configuration
     */
    public static void writeBack(SpoonFacade spoon, HostAnalyzer.InsertionPoints insertionPoints,
        StmtInserter.Result data, String outPath, String dataOutPath, int arraySize
    ) {
        
        // For each class
        for (Map.Entry<String, Map<String, SpoonMethod>> classEntry: insertionPoints.methods.entrySet()) {
            
            String className = classEntry.getKey();
            Map<String, Map<String, Set<String>>> localVarInCls = data.localVarMap.get(className);
            Map<String, SpoonMethod> methodObjList = classEntry.getValue();
            Logger.debug("Class Name: " + className);
            if (methodObjList == null || localVarInCls == null) {
                continue;
            }
            // for each method
            for (String methodName: methodObjList.keySet()) {
                Logger.debug("Method Name: " + methodName);
                Writer.insertLocalVars(spoon, methodObjList.get(methodName), localVarInCls.get(methodName), data);
            }
        }
                
        spoon.writeBackAST(outPath, data.requiredImports,
            Writer.allFuzzerStr(data, arraySize), Writer.requiredClazStr(data),
            insertionPoints.args);
        
        if (dataOutPath != null && data.mutatorUsage != null) {
            Writer.writeJson(data.mutatorUsage, dataOutPath);
        }
    }

}
