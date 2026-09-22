package com.allfuzzer.preprocess;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.allfuzzer.config.Config;
import com.allfuzzer.domain.MutatorUnit;
import com.allfuzzer.domain.MutatorUnit.MutType;
import com.allfuzzer.spoon.SpoonFacade;
import com.allfuzzer.spoon.SpoonCode.*;
import com.allfuzzer.util.Logger;

/**
 * MutatorAnalyzer uses Spoon to parse mutator source files and extract MutatorUnit objects.
 * It identifies special variables prefixed with $gbvar, $lval, and $expr to categorize them.
 */
public class MutatorAnalyzer {

    private static String getUniqueFuncName(String baseName) {
        return String.format("$func_%s_%d", baseName, System.nanoTime());
    }

    private static String getUniqueClassName(String baseName) {
        return String.format("%s_%d", baseName, System.nanoTime());
    }

    private static void setMutInfo(MutatorUnit mu, SpoonMethod method, Map<String, String> funcNamingMap, Map<String, String> classNamingMap) {
        
        if (method.getSimpleName().startsWith("$mut")) {
            mu.setMutType(MutatorUnit.MutType.STATEMENT);
            mu.setRetType(MutatorAnalyzer.replaceClassNames(method.getRetType(), classNamingMap));
            String after = MutatorAnalyzer.replaceNames(MutatorAnalyzer.cutRetEnd(method.getStmts()), funcNamingMap, classNamingMap);
            Logger.debug("\n===\n" + mu.getSrcFilePath() + "\nMutator stmts after replacement: \n" + after + "\n===\n");
            mu.setMutString(after);
        }
        else if (method.getSimpleName().startsWith("$new")) {
            mu.setMutType(MutatorUnit.MutType.NEW);
            mu.setRetType(MutatorAnalyzer.replaceClassNames(method.getRetType(), classNamingMap));
            mu.setMutString(MutatorAnalyzer.replaceNames(MutatorAnalyzer.cutRetEnd(method.getStmts()), funcNamingMap, classNamingMap));
        }
        else if (method.getSimpleName().startsWith("$init")) {
            if (method.getParams().size() < 1) {
                throw new IllegalArgumentException("$init method must have exactly one parameter");
            }
            for (Map.Entry<String, String> entry : mu.getLocalVars().entrySet()) {
                String varName = entry.getKey();
                String varType = MutatorAnalyzer.replaceClassNames(entry.getValue(), classNamingMap);
                Logger.debug(
                    String.format("Init mutator param: %s of type %s", varName, varType)
                );
            }
            mu.setMutType(MutatorUnit.MutType.INIT);
            mu.setRetType(MutatorAnalyzer.replaceClassNames(method.data().getParameters().get(0).getType().toString(), classNamingMap));
            mu.setMutString(MutatorAnalyzer.replaceNames(method.getStmts(), funcNamingMap, classNamingMap));
            for (Map.Entry<String, String> entry : mu.getLocalVars().entrySet()) {
                String varName = entry.getKey();
                String varType = MutatorAnalyzer.replaceClassNames(entry.getValue(), classNamingMap);
                Logger.debug(
                    String.format("Init mutator param: %s of type %s", varName, varType)
                );
            }
        }
        else if (method.getSimpleName().startsWith("$check")) {
            if (method.getParams().size() < 1) {
                throw new IllegalArgumentException("$check method must have exactly one parameter");
            }
            mu.setMutType(MutatorUnit.MutType.CHECK);
            mu.setRetType(MutatorAnalyzer.replaceClassNames(method.data().getParameters().get(0).getType().toString(), classNamingMap));
            mu.setMutString(MutatorAnalyzer.replaceNames(method.getStmts(), funcNamingMap, classNamingMap));
        }
        else {
            // Skip methods that do not start with $func or $expr or $stmt
            // Can add other categories if needed
            throw new IllegalArgumentException("Method name does not start with $func, $expr, or $stmt");
        }
    }
    
    private static String cutRetEnd(String input) {
        String ret = new String(input);
        if (ret.indexOf("return;") != -1) {
            ret = ret.replace("return;", "").trim();
        }
        
        int retIndex = ret.indexOf("return");
        if (retIndex != -1) {
            ret = ret.substring(retIndex + "return".length()).trim();
        }
        if (ret.endsWith(";")) {
            ret = ret.substring(0, ret.length() - 1).trim();
        }
        return ret;
    }

    private static void addParams(MutatorUnit mu, List<SpoonParameter> params, Map<String, String> classNamingMap) {
        Map<String, String> localVarMap = new HashMap<>();
        Map<String, String> exprMap = new HashMap<>();
        Map<String, String> constMap = new HashMap<>();
        for (SpoonParameter param : params) {
            String name = param.getSimpleName();
            String type = MutatorAnalyzer.replaceClassNames(param.getType(), classNamingMap);
            if (name.startsWith("$lval")) {
                localVarMap.put(name, type);
            } else if (name.startsWith("$expr")) {
                exprMap.put(name, type);
            } else if (name.startsWith("$const")) {
                constMap.put(name, type);
            } else {
                throw new IllegalArgumentException(
                    String.format("Parameter %s has an illegal prefix.", name)
                );
            }
        }
        mu.setLocalVars(localVarMap);
        mu.setExprs(exprMap);
        mu.setConsts(constMap);
    }

    private static Map<String, MutatorUnit> createFuncMutators(List<SpoonMethod> methods,
        Map<String, String> funcNamingMap, Map<String, String> classNamingMap
    ) {
        Map<String, MutatorUnit> funcs = new HashMap<>();
        for (SpoonMethod method: methods) {
            if (method.getSimpleName().startsWith("$func")) {
                MutatorUnit mu = new MutatorUnit();
                String stmts = MutatorAnalyzer.replaceNames(method.getStmts(), funcNamingMap, classNamingMap);
                List<String> paramTypes = new ArrayList<>();
                for (String param : method.getParamTypes()) {
                    paramTypes.add(MutatorAnalyzer.replaceClassNames(param, classNamingMap));
                }
                mu.setFuncInfo(method.getSimpleName(), paramTypes, method.getParamNames(), stmts);
                mu.setRetType(MutatorAnalyzer.replaceClassNames(method.getRetType(), classNamingMap));
                funcs.put(method.getSimpleName(), mu);
            }
        }
        return funcs;
    }

    private static String replaceNames(String stmts, Map<String, String> funcNamingMap, Map<String, String> classNamingMap) {
        if (funcNamingMap != null) {
            for (Map.Entry<String, String> entry : funcNamingMap.entrySet()) {
                String originalName = entry.getKey();
                String newName = entry.getValue();
                stmts = stmts.replace(originalName + "(", newName + "(");
            }
        }
        if (classNamingMap != null) {
            for (Map.Entry<String, String> entry : classNamingMap.entrySet()) {
                String originalName = entry.getKey();
                String newName = entry.getValue();
                stmts = stmts.replace(originalName, newName);
            }
        }
        return stmts;
    }

    private static String replaceClassNames(String stmts, Map<String, String> classNamingMap) {
        if (classNamingMap != null) {
            for (Map.Entry<String, String> entry : classNamingMap.entrySet()) {
                String originalName = entry.getKey();
                String newName = entry.getValue();
                stmts = stmts.replace(originalName, newName);
            }
        }
        return stmts;
    }
    
    /**
     * Analyze a mutator source file and extract MutatorUnit(s)
     * @param srcPathString Path to the mutator source file
     * @param priority_ Priority to assign to the mutators
     * @return Set of MutatorUnit extracted from the source file
     */
    public static List<MutatorUnit> analyzeMutator(String srcPathString, int priority_) {
        
        List<MutatorUnit> mutatorUnits = new java.util.ArrayList<>();
        SpoonFacade spoon = new SpoonFacade(srcPathString, false, true);
                
        // Build class renaming map
        Map<String, String> clsNamingMap = new HashMap<>();
        for (SpoonCls clazz: spoon.getAllClasses()) {
            if (clazz.getSimpleName().startsWith("$cls")) {
                clsNamingMap.put(
                    clazz.getSimpleName(),
                    MutatorAnalyzer.getUniqueClassName(clazz.getSimpleName())
                );
            }
        }
        for (SpoonInterFace clazz: spoon.getAllInterfaces()) {
            if (clazz.getSimpleName().startsWith("$cls")) {
                clsNamingMap.put(
                    clazz.getSimpleName(),
                    MutatorAnalyzer.getUniqueClassName(clazz.getSimpleName())
                );
            }
        }

        Map<String, MutatorUnit> clazzs = new HashMap<>();
        for (SpoonCls clazz: spoon.getAllClasses()) {
            if (clazz.getSimpleName().startsWith("$cls")) {
                MutatorUnit classMu = new MutatorUnit();
                classMu.setSrcFilePath(srcPathString);
                classMu.setMutType(MutType.CLASS);
                classMu.setMutString(MutatorAnalyzer.replaceClassNames(clazz.data().prettyprint(), clsNamingMap));
                clazz.setSimpleName(clsNamingMap.get(clazz.getSimpleName()));
                clazzs.put(clazz.getSimpleName(), classMu);
            }
        }
        for (SpoonInterFace clazz: spoon.getAllInterfaces()) {
            if (clazz.getSimpleName().startsWith("$cls")) {
                MutatorUnit classMu = new MutatorUnit();
                classMu.setSrcFilePath(srcPathString);
                classMu.setMutType(MutType.CLASS);
                classMu.setMutString(MutatorAnalyzer.replaceClassNames(clazz.data().prettyprint(), clsNamingMap));
                clazz.setSimpleName(clsNamingMap.get(clazz.getSimpleName()));
                clazzs.put(clazz.getSimpleName(), classMu);
            }
        }
        
        for (SpoonCls clazz: spoon.getAllClasses()) {
            if (clazz.getSimpleName().startsWith("$cls")) {
                continue; // Skip class definitions
            }

            // Add global vars ($gbvar)
            Map<String, String> gbVarMap = new HashMap<>();
            int priority = priority_;
            for (SpoonField field : clazz.getFields()) {
                String name = field.getSimpleName();
                String type = MutatorAnalyzer.replaceClassNames(field.getType(), clsNamingMap);
                if (name.startsWith("$gbvar")) {
                    gbVarMap.put(name, type);
                    Logger.debug(
                        String.format(
                            "Mutator %s: added global var %s of type %s.",
                            srcPathString, name, type)
                    );
                }
                else if (name.equals("$priority")) {
                    // Set mutator priority
                    if (priority != -1) {
                        if (field.getInitialIntOrNull() != null) {
                            priority = (int) field.getInitialIntOrNull();
                            Logger.debug(
                                String.format(
                                    "Mutator %s: set priority to %d.",
                                    srcPathString, priority)
                            );
                        } 
                    }
                }
                else {
                    Logger.warning(
                        String.format(
                            "Mutator %s: skipping field %s.%s as it has an illegal prefix.",
                            srcPathString, clazz.getSimpleName(), name, name)
                    );
                    continue;
                }
            }

            // Old Name -> New Name
            List<SpoonMethod> allMethods = spoon.getAllFuncs(clazz, false);
            Map<String, String> funcNamingMap = new HashMap<>();
            
            // Rename self-defined functions to avoid name conflicts
            for (SpoonMethod method: allMethods) {
                if (!method.getSimpleName().startsWith("$")) {
                    String oldName = method.getSimpleName();
                    String newName = MutatorAnalyzer.getUniqueFuncName(oldName);
                    funcNamingMap.put(oldName, Config.GbClsName + "." + newName);
                    method.setSimpleName(newName);
                }
            }

            // Create MutatorUnit for self-defined functions
            Map<String, MutatorUnit> funcs = MutatorAnalyzer.createFuncMutators(allMethods, funcNamingMap, clsNamingMap);
            for (Map.Entry<String, MutatorUnit> entry : funcs.entrySet()) {
                MutatorUnit funcMu = entry.getValue();
                funcMu.setSrcFilePath(srcPathString);
                funcMu.setMutType(MutType.FUNCTION);
                funcMu.setPriority(priority);
                funcMu.setImports(spoon.getImports());
                funcMu.setGBVars(gbVarMap); // Inherit global vars
                funcMu.setNeedFuncs(funcs);
                funcMu.setNeedClass(clazzs);
                Logger.debug(
                    String.format("Mutator %s: added function mutator %s.", srcPathString, entry.getKey())
                );
                mutatorUnits.add(funcMu);
            }

            // Add functions ($func, $mut, $new, $init, $check, $const)
            for (SpoonMethod method: allMethods) {
                
                // Ignore non-mutator methods
                if (method.getSimpleName().startsWith("$func")) {
                    continue;
                }
                
                // Set function name and return type
                try {
                    MutatorUnit mu = new MutatorUnit();
                    mu.setSrcFilePath(srcPathString);
                    mu.setPriority(priority);
                    mu.setImports(spoon.getImports());
                    mu.setGBVars(gbVarMap); // Inherit global vars
                    mu.setNeedFuncs(funcs);
                    mu.setNeedClass(clazzs);
                    MutatorAnalyzer.addParams(mu, method.getParams(), clsNamingMap); // Add parameters ($lval, $expr...)
                    MutatorAnalyzer.setMutInfo(mu, method, funcNamingMap, clsNamingMap);
                    mutatorUnits.add(mu);
                } catch (IllegalArgumentException e) {
                    Logger.warning(
                        String.format("Mutator %s: %s.%s skipped: %s", srcPathString,
                            clazz.getSimpleName(), method.getSimpleName(), e.getMessage())
                    );
                    continue;
                }
            }
        }
        return mutatorUnits;
    }

}
