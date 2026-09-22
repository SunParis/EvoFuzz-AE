package com.allfuzzer.inserter;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Collections;

import com.allfuzzer.config.*;
import com.allfuzzer.domain.MutatorUnit;
import com.allfuzzer.domain.MutatorUnit.MutType;
import com.allfuzzer.preprocess.HostAnalyzer.InsertionPoints;
import com.allfuzzer.preprocess.HostAnalyzer.InsertionPoints.InsertPoint;
import com.allfuzzer.preprocess.MutatorScanner;
import com.allfuzzer.spoon.SpoonCode.SpoonMethod;
import com.allfuzzer.util.RandomChooser;
import com.allfuzzer.util.Logger;

/**
 * StmtInserter handles the insertion of mutator statements into the host code
 *  at the identified insertion points, managing variable naming to avoid conflicts.
 */
public class StmtInserter {

    public static class Result {
        
        // Map: ClassName -> (MethodUniqueName -> (Type -> LocalVarName))
        public Map<String, Map<String, Map<String, Set<String>>>> localVarMap;
        
        // Map: Type -> GBVarName
        public Map<String, Set<String>> gbVarMap;
        
        // Required imports for the inserted mutators
        public Set<String> requiredImports;

        // Required functions for the inserted mutators
        public Map<String, String> requiredFuncs;

        // Required functions for the inserted mutators
        public Map<String, String> requiredClazzs;

        // Required muts for alloc new types (Type -> List of MutatorUnit)
        // e.g. "new Object()"
        public Map<String, List<MutatorUnit>> requiredAlloc;

        // Required muts for initialization (Type -> List of MutatorUnit)
        public Map<String, List<MutatorUnit>> requiredInit;

        // Required muts for checks (Type -> List of MutatorUnit)
        public Map<String, List<MutatorUnit>> requiredChecks;

        public Map<String, Map<String, Integer>> mutatorUsage;

        public int count = 0;
        
        public Result() {
            this.localVarMap = new HashMap<>();
            this.gbVarMap = new HashMap<>();
            this.requiredImports = new HashSet<>();
            this.requiredFuncs = new HashMap<>();
            this.requiredClazzs = new HashMap<>();
            this.requiredAlloc = new HashMap<>();
            this.requiredInit = new HashMap<>();
            this.requiredChecks = new HashMap<>();
        }

        public int Count() {
            return this.count;
        }

        public void addImports(Set<String> imports) {
            this.requiredImports.addAll(imports);
            for (String imp : imports) {
                Logger.debug("Add required import: " + imp);
            }
        }

        public void addFuncs(Map<String, MutatorUnit> funcs) {
            if (funcs != null && !funcs.isEmpty()) {
                for (Map.Entry<String, MutatorUnit> entry : funcs.entrySet()) {
                    String funcName = entry.getKey();
                    MutatorUnit funcMut = entry.getValue();
                    Logger.debug("Try to add required function: " + funcName + " ...");
                    if (!this.requiredFuncs.containsKey(funcName)) {
                        this.requiredFuncs.put(funcName, this.replaceFuncBodyGBVar(funcMut));
                        Logger.debug("Add required function: " + funcName);
                    }
                }
            }
        }

        public void addClazzs(Map<String, MutatorUnit> clazzs) {
            if (clazzs != null && !clazzs.isEmpty()) {
                for (Map.Entry<String, MutatorUnit> entry : clazzs.entrySet()) {
                    String clazzsName = entry.getKey();
                    MutatorUnit clazzsMut = entry.getValue();
                    if (!this.requiredClazzs.containsKey(clazzsName)) {
                        this.requiredClazzs.put(clazzsName, clazzsMut.getMutString());
                    }
                }
            }
        }

        private String replaceFuncBodyGBVar(MutatorUnit selectedMutator) {
            // OldName -> NewName
            Map<String, String> tmpMap = new HashMap<>();
            // For each global variable in the mutator unit, replace with a new unique name
            for (Map.Entry<String, String> gbvar : selectedMutator.getGBVars().entrySet()) {
                String name = gbvar.getKey();
                String type = gbvar.getValue();
                if (!this.gbVarMap.containsKey(type)) {
                    this.gbVarMap.put(type, new HashSet<>());
                }
                String newGB = VarPool.getGBVar(type);
                this.gbVarMap.get(type).add(newGB);
                tmpMap.put(name, newGB);
            }
            return selectedMutator.getFuncStr(tmpMap);
        }

        public void addVarMappings(MutatorScanner.ScanResult mutators, Map<String, Set<String>> varMap) {
            for (String type: varMap.keySet()) {
                Logger.debug("Add vars of type " + type + ": " + varMap.get(type));
                if (!this.requiredAlloc.containsKey(type)) {
                    if (mutators.newMap.containsKey(type)) {
                        this.requiredAlloc.put(type, mutators.newMap.get(type));
                    }
                    else {
                        boolean is_basic_type = VarPool.isBasicType(type);
                        boolean is_array = type.indexOf("[]") != -1;
                        if (is_array) {
                            String baseType = type.replace("[]", "");
                            is_basic_type = VarPool.isBasicType(baseType);
                        }
                        
                        List<MutatorUnit> typeList = new ArrayList<>();
                        if (is_basic_type) {
                            Logger.debug("No allocation mutator found for type: " + type + ", try to add a default one.");
                            if (!is_array) {
                                typeList.add(new MutatorUnit());
                                typeList.get(0).setMutType(MutType.NEW);
                                typeList.get(0).setMutString(VarPool.getConst(type));
                            }
                            else {
                                typeList.add(new MutatorUnit());
                                typeList.get(0).setMutType(MutType.NEW);
                                typeList.get(0).setMutString("new " + type.replace("[]", "[AllFuzzerDefs.ARRAY_SIZE]"));
                            }   
                        }
                        else {
                            Logger.warning("No allocation mutator found for type: " + type + ", try to add a default one.");
                            typeList.add(new MutatorUnit());
                            typeList.get(0).setMutType(MutType.NEW);
                            typeList.get(0).setMutString("null");
                        }
                        mutators.newMap.put(type, typeList);
                        this.requiredAlloc.put(type, typeList);
                    }
                }

                if (!this.requiredInit.containsKey(type) && mutators.initMap.containsKey(type)) {
                    this.requiredInit.put(type, mutators.initMap.get(type));
                }

                if (!this.requiredChecks.containsKey(type) && mutators.checkMap.containsKey(type)) {
                    this.requiredChecks.put(type, mutators.checkMap.get(type));
                }
            }
        }

    }

    public static int maxInsertMutNum = 2;
    
    // private static String[] splitByMiddleNewline(String s) {
    //     if (s == null) return new String[] { "", "" };

    //     // Count number of newlines
    //     int n = 0;
    //     for (int i = 0; i < s.length(); i++) {
    //         if (s.charAt(i) == '\n') n++;
    //     }

    //     // If no newlines, return the original string and an empty string
    //     if (n == 0) return new String[] { s, "" };

    //     // Choose the middle newline
    //     int k = (n % 2 == 1) ? (n + 1) / 2 : n / 2;

    //     // Find the index of the k-th newline
    //     int count = 0, idx = -1;
    //     for (int i = 0; i < s.length(); i++) {
    //         if (s.charAt(i) == '\n') {
    //             count++;
    //             if (count == k) {
    //                 idx = i;
    //                 break;
    //             }
    //         }
    //     }

    //     String first = s.substring(0, idx);
    //     String second = s.substring(idx + 1);
    //     return new String[] { first, second };
    // }


    /**
     * Get new variable mappings for the local variables in the mutator unit.
     * @param mut the mutator unit
     * @return a map of original variable names to new variable names
     */
    private static Map<String, String> getLocalVar(String clzzname, SpoonMethod methodobj, MutatorUnit mut) {
        Map<String, String> ret = new HashMap<>();
        Logger.debug("=================================================");
        for (Map.Entry<String, String> entry : mut.getLocalVars().entrySet()) {
            String name = entry.getKey();
            String type = entry.getValue();
            String newName = ExprInserter.getLocalVar(clzzname, methodobj, type);
            ret.put(name, newName);
            Logger.debug("Get local var: " + name + " of type " + type + " as " + newName);
        }
        Logger.debug("=================================================");
        return ret;
    }

    /**
     * Get new variable mappings for the global variables in the mutator unit.
     * @param mut the mutator unit
     * @return a map of original variable names to new variable names
     */
    private static Map<String, String> getGBVar(MutatorUnit mut) {
        Map<String, String> ret = new HashMap<>();
        for (Map.Entry<String, String> entry : mut.getGBVars().entrySet()) {
            String name = entry.getKey();
            String type = entry.getValue();
            String newName = VarPool.getGBVar(type);
            ret.put(name, newName);
        }
        return ret;
    }

    /**
     * Get new expressions mappings for the $expr names in the mutator unit.
     * @param mut the mutator unit
     * @param results the Result object to update
     * @return a map of original $expr names to new expression names
     */
    private static Map<String, String> getExpr(MutatorUnit mut, InsertPoint insertPos, Result results, WeightedPicker stmtPicker) {
        Map<String, String> ret = new HashMap<>();
        for (Map.Entry<String, String> entry : mut.getExprs().entrySet()) {
            String name = entry.getKey();
            String type = entry.getValue();            
            String newName = ExprInserter.insert(type, insertPos.className, insertPos.getUniqueMethodName(), results, stmtPicker);
            ret.put(name, newName);
        }
        return ret;
    }

    /**
     * Get constant mappings for the constants in the mutator unit.
     * @param mut the mutator unit
     * @return a map of original constant names to constant values
     */
    private static Map<String, String> getConsts(MutatorUnit mut) {
        Map<String, String> ret = new HashMap<>();
        for (Map.Entry<String, String> entry : mut.getConsts().entrySet()) {
            String name = entry.getKey();
            String type = entry.getValue();
            ret.put(name, VarPool.getConst(type, name.startsWith("$const_small")));
            Logger.debug("Get const: " + name + " of type " + type + " as " + ret.get(name));
        }
        return ret;
    }

    /**
     * Generate simple statements to fill in the mutator if needed.
     * @param insertPos the insertion point context
     * @param N number of simple statements to generate
     * @return the generated simple statements
     */
    private static List<String> getSimpleStmts(InsertPoint insertPos, int N) {
        if (N <= 0) {
            return List.of("");
        }
        final String[] exprType = {
            "int", "long", "float", "double", "char", "String"
        };
        List<String> ret = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String type = exprType[RandomChooser.nextInt(exprType.length)];
            String lval = ExprInserter.getLocalVar(
                insertPos.className,
                insertPos.methodobj, type);
            String expr = ExprInserter.getSimpleExpr(type, insertPos.className, insertPos.getUniqueMethodName());
            if (expr == null || expr.isEmpty() || expr.equals("null")) {
                expr = ExprInserter.getLocalVar(
                        insertPos.className, 
                        insertPos.methodobj, type) + "+";
                expr += 
                    ExprInserter.getLocalVar(
                        insertPos.className,
                        insertPos.methodobj, type);
            }
            ret.add(lval + " = " + expr + "; \n");
        }
        return ret;
    }

     /**
     * Generate the mutator string with updated variable names.
     * @param ret the Result object to update
     * @param selectedMutator the mutator unit being processed
     * @param stmtPicker the weighted picker for selecting statement mutators
     * @return the generated mutator string
     */
    private static String generateMutStr(
        Result ret, InsertPoint insertPos, MutatorUnit selectedMutator,
        WeightedPicker stmtPicker, int depth
    ) {
        String res = null;
        int nxtDepth = depth + 1;
        ret.addImports(selectedMutator.getImports());
        ret.addFuncs(selectedMutator.getNeedFuncs());
        ret.addClazzs(selectedMutator.getNeedClass());
        
        if (selectedMutator.getMutType() == MutType.FUNCTION) {
            List<String> paramList = new ArrayList<>();
            for (String paramType: selectedMutator.getParamTypes()) {
                paramList.add(
                    ExprInserter.insert(paramType, insertPos.className, insertPos.getUniqueMethodName(), ret, stmtPicker));
            }
            res = selectedMutator.getMutString(paramList) + ";";
            Logger.debug("Generated function-level mutator: " + res);
        }
        else {
            Map<String, String> newExprs = StmtInserter.getExpr(selectedMutator, insertPos, ret, stmtPicker);
            Map<String, String> newLocalVars = StmtInserter.getLocalVar(insertPos.className, insertPos.methodobj, selectedMutator);
            Map<String, String> newGBVars = StmtInserter.getGBVar(selectedMutator);
            Map<String, String> newConsts = StmtInserter.getConsts(selectedMutator);
            
            int newStmtNum = selectedMutator.getStmtNum();

            List<String> newStmts = new ArrayList<>(newStmtNum);
            if (newStmtNum > 0) {
                // Generate 4 simple statements to fill in
                List<String> simpleStmts = insertPos.insertObj.extractAndRemoveStmts(2);
                simpleStmts.addAll(StmtInserter.getSimpleStmts(insertPos, 2));
                Collections.shuffle(simpleStmts);

                // Add more statements recursively
                List<MutatorUnit> newStmtMuts = new ArrayList<>();
                MutatorUnit nxtMut = stmtPicker.peekNextMutator("void");
                while (nxtMut.getStmtNum() == 0 && !stmtPicker.meetEnd("void")) {
                    newStmtMuts.add(stmtPicker.nextMutator("void"));
                    if (newStmtMuts.size() >= StmtInserter.maxInsertMutNum) {
                        break;
                    }
                      
                    nxtMut = stmtPicker.peekNextMutator("void");
                }
                if (nxtMut.getStmtNum() != 0 && depth <= 3 && !stmtPicker.meetEnd("void")) {
                    nxtMut = stmtPicker.nextMutator("void");
                }
                else {
                    nxtMut = null;
                }

                // Randomly select up to 6 statement-level mutators to insert
                if (nxtMut != null) {
                    newStmtMuts.add(nxtMut);
                }

                for (int i = 0; i < newStmtNum; i++) {
                    String complexStmt = "";
                    for (MutatorUnit mut: newStmtMuts) {
                        complexStmt += ("\n" + 
                            StmtInserter.generateMutStr(
                                ret, insertPos, mut,
                                stmtPicker, nxtDepth
                            )
                        );
                    }

                    String stmt = "";
                    int mid = RandomChooser.nextInt(simpleStmts.size());
                    for (int j = 0; j < mid; j++) {
                        stmt += ("\n" + simpleStmts.get(j));
                    }
                    stmt += complexStmt;
                    for (int j = mid; j < simpleStmts.size(); j++) {
                        stmt += ("\n" + simpleStmts.get(j));
                    }
                    if (i != 0) {
                        stmt = stmt.replace("// AllFuzzer: insert Mutators Here", "");
                    }
                    newStmts.add(stmt);
                }
            }
            res = selectedMutator.getMutString(newGBVars, newLocalVars, newExprs, newConsts, newStmts);
            
            Logger.debug("Current Res: \n" + res);
            Logger.debug("Current VarPool: \n" + VarPool.to_string());
        }

        return res;
    }

    /**
     * Generate the mutator string with updated variable names.
     * @param ret the Result object to update
     * @param selectedMutator the mutator unit being processed
     * @param stmtPicker the weighted picker for selecting statement mutators
     * @return the generated mutator string
     */
    private static String generateMutStr(
        Result ret, InsertPoint insertPos, 
        MutatorUnit selectedMutator, WeightedPicker stmtPicker
    ) {
        return StmtInserter.generateMutStr(ret, insertPos, selectedMutator, stmtPicker, 0);
    }

    private static String addInsertFlag(String mutStr) {
        if (Config.isAddInsertFlag())
            return "// AllFuzzer: insert Mutators Here\n" + mutStr;
        return mutStr;
    }
        
    /**
     * Insert mutators into the host insertion points randomly.
     * @param insertionPoints insertion points analyzed from the host code
     * @param mutators available mutators categorized by statement type
     * @param totalNum total number of mutators to insert
     * @param inOrder whether to select mutators in order or randomly
     * @param inOnePoint whether to insert all mutators at a single insertion point
     * @return Result object containing the insertion map and variable mappings
     */
    public static Result insert(
        InsertionPoints insertionPoints, MutatorScanner.ScanResult mutators, 
        long totalNum, boolean inOrder, boolean inOnePoint
    ) {
        
        Result ret = new Result();

        WeightedPicker stmtPicker = new WeightedPicker(mutators.mutatorMap, inOrder);
        InsertPoint insertPos = null;
        if (inOnePoint && Config.isInsertInPrevPoint()) {
            insertPos = insertionPoints.getPrevInsertPoint();
            if (insertPos == null) {
                insertPos = insertionPoints.getRandomInsertPoint();
            }
        } else if (inOnePoint) {
            insertPos = insertionPoints.getRandomInsertPoint();
        } else if (!inOnePoint && Config.isInsertInPrevPoint()) {
            insertPos = insertionPoints.getRandomPrevInsertPoint();
            if (insertPos == null) {
                insertPos = insertionPoints.getRandomInsertPoint();
            }
        }

        String mutStr = "";
        if (inOrder) {
            StmtInserter.maxInsertMutNum = 10000;
        }

        ret.addImports(insertionPoints.imports);

        while (stmtPicker.getCount() < totalNum) {
            
            MutatorUnit selectedMutator = stmtPicker.nextMutator("void");
            if (selectedMutator == null) {
                Logger.warning("No available statement mutator found for insertion.");
                return ret;
            }

            // Generate mutator string
            if (insertPos == null) {
                assert inOnePoint: "Insert position should not be null if not inserting in one point.";
                if (Config.isInsertInPrevPoint()) {
                    insertPos = insertionPoints.getRandomPrevInsertPoint();
                }
                if (insertPos == null) {
                    insertPos = insertionPoints.getRandomInsertPoint();
                }
                if (insertPos == null) {
                    Logger.warning("No available insertion point found for insertion. Skipping mutator insertion.");
                    return ret;
                }
                mutStr = StmtInserter.generateMutStr(ret, insertPos, selectedMutator, stmtPicker);
                insertionPoints.insertStatements(insertPos.insertObj, List.of(StmtInserter.addInsertFlag(mutStr)), inOnePoint);
                Logger.debug("Inserted mutator " + (stmtPicker.getCount()) + "/" + totalNum + " : \n" + mutStr);
                insertPos = null;
            }
            else {
                String tmp = StmtInserter.generateMutStr(ret, insertPos, selectedMutator, stmtPicker);
                mutStr += tmp + "\n";
                Logger.debug("Inserted mutator " + (stmtPicker.getCount()) + "/" + totalNum + " : \n" + selectedMutator.getSrcFilePath());
                
                if (stmtPicker.meetEnd("void") && stmtPicker.getCount() < totalNum) {
                    insertionPoints.insertStatements(insertPos.insertObj, List.of(StmtInserter.addInsertFlag(mutStr)), inOnePoint);
                    // insertPos = insertionPoints.getRandomInsertPoint();
                    mutStr = "";
                }
                
            }
        }

        if (inOnePoint) {
            Logger.debug("Final inserted mutator at one point: \n" + mutStr);
            insertionPoints.insertStatements(insertPos.insertObj, List.of(StmtInserter.addInsertFlag(mutStr)), inOnePoint);
            Logger.debug("After insertion: \n " + insertPos.insertObj.data().toString());
        }

        ret.gbVarMap = VarPool.getGBVars();
        ret.localVarMap = VarPool.getLocalVars();
        ret.mutatorUsage = stmtPicker.getMutatorUsageCount();
        ret.count = stmtPicker.getCount();

        ret.addVarMappings(mutators, ret.gbVarMap);
        ret.addVarMappings(mutators, VarPool.getLocalVarsTypMap());
        Logger.debug("Vars: \n" + VarPool.to_string());
        
        return ret;
    }    

}
