package com.allfuzzer.inserter;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import com.allfuzzer.domain.MutatorUnit;
import com.allfuzzer.domain.MutatorUnit.MutType;
import com.allfuzzer.inserter.StmtInserter.Result;
import com.allfuzzer.spoon.SpoonCode.SpoonMethod;
import com.allfuzzer.util.Logger;
import com.allfuzzer.util.RandomChooser;

/**
 * ExprInserter handles the generation and insertion of mutator expressions
 *  into the host code, managing variable naming to avoid conflicts.
 */
public class ExprInserter {
    
    // private static Map<String, WeightedPicker> mutators;
    private static int depth = 0;

    /**
     * Check if the given type is a basic numeric type.
     * @param type The type to check.
     * @return True if the type is a basic numeric type, false otherwise.
     */
    private static boolean isNumType(String type) {
        switch (type) {
            case "int":
            case "long":
            case "short":
            case "byte":
            case "float":
            case "double":
            case "char":
            case "Integer":
            case "Long":
            case "Short":
            case "Byte":
            case "Float":
            case "Double":
            case "Character":
                return true;
            default:
                return false;
        }
    }

    private static String basicTypeformWrapper(String wrapperType) {
        switch (wrapperType) {
            case "Integer":
                return "int";
            case "Long":
                return "long";
            case "Short":
                return "short";
            case "Byte":
                return "byte";
            case "Float":
                return "float";
            case "Double":
                return "double";
            case "Character":
                return "char";
            default:
                return wrapperType;
        }
    }

    /**
     * Convert an expression from one type to another.
     * @param target_type the desired target type
     * @param expr_type the current type of the expression
     * @param expr the expression string
     * @return the converted expression string
     */
    private static String transExprType(String target_type, String expr_type, String expr) {
        if (target_type.equals(expr_type)) {
            return "(" + expr + ")";
        }
        String ret = expr;
        if (expr_type.charAt(0) >= 'A' && expr_type.charAt(0) <= 'Z') {
            ret = "((" + ExprInserter.basicTypeformWrapper(expr_type) + ")(" + ret + "))";
        }
        else {
            ret = "(" + ret + ")";
        }
        if (target_type.charAt(0) >= 'A' && target_type.charAt(0) <= 'Z') {
            ret = target_type + ".valueOf((" + ExprInserter.basicTypeformWrapper(target_type) + ")" + ret + ")";
        }
        else {
            ret = "((" + target_type + ")" + ret + ")";
        }
        return ret;
    }

    /**
     * Get a random basic numeric type different from the given type.
     * @param exclude The type to exclude.
     * @return A random basic numeric type different from 'except'.
     */
    private static String getRandomBasicNumType(String exclude) {
        String[] basicTypes = {
            "int", "long", "short", "byte", "float", "double", "char",
            "Integer", "Long", "Short", "Byte", "Float", "Double", "Character"
        };
        String type = exclude;
        while (type.equals(exclude)) {
            type = basicTypes[RandomChooser.nextInt(basicTypes.length)];
        }
        return type;
    }

    /**
     * Get a random constant for the given type.
     * @param type The desired type of the constant.
     * @return A string representing a random constant of the given type.
     */
    private static String getRandomConst(String type) {
        return VarPool.getConst(type);
    }

    /**
     * Get a local variable of the given type from the specified class and method.
     * @param className The name of the class, where the method is located.
     * @param methodName The name of the method, where the variable is defined.
     * @param type The desired type of the variable.
     * @return A string representing a local variable of the given type.
     */
    public static String getLocalVar(String className, String methodName, String type) {
        return VarPool.getLocalVar(className, methodName, type);
    }

    /**
     * Get a local variable of the given type from the specified class and SpoonMethod object.
     * @param className The name of the class, where the method is located.
     * @param methodobj The SpoonMethod object representing the method.
     * @param type The desired type of the variable.
     * @return A string representing a local variable of the given type.
     */
    public static String getLocalVar(String className, SpoonMethod methodobj, String type) {
        String ret = methodobj.getRandomVariableOfType(type);
        if (RandomChooser.nextInt(10) < 7 && ret != null) {
            return ret;
        }
        else {
            return VarPool.getLocalVar(className, methodobj.getUniqueName(), type);
        }
    }

    /**
     * Get a simple expression for the given type.
     * @param type The desired type of the expression.
     * @param className The name of the class, where the method is located.
     * @param fnName The name of the method, where the variable is defined.
     * @return A string representing a simple expression. (constant or variable without operations)
     */
    public static String getSimpleExpr(String type, String className, String fnName) {
        String ret;
        int choice;
        if (!isNumType(type) && !type.endsWith("Vector") && !type.equals("Object")) {
            choice = RandomChooser.nextInt() % 2 + 1;
        }
        else {
            choice = RandomChooser.nextInt() % 3;
        }
        switch (choice) {
            case 0:
                ret = ExprInserter.getRandomConst(type);
                if (!ret.equals("null")) {
                    return ret;
                }
                // fall through if no constant available
            case 1:
                return ExprInserter.getLocalVar(className, fnName, type);
                // fall through if insertPos is null
            case 2:
                return VarPool.getGBVar(type);
            default:
                return ExprInserter.getRandomConst(type);
        }
    }

    /**
     * Get a random mutator expression for the given type.
     * @param type The desired type of the expression.
     * @param className The name of the class, where the method is located.
     * @param fnName The name of the method, where the variable is defined.
     * @param result The Result object to collect imports and dependencies.
     * @param stmtPicker The WeightedPicker for selecting mutator units.
     * @return A string representing the mutator expression.
     */
    public static String insert(String type, 
        String className, String fnName, 
        Result result, WeightedPicker stmtPicker
    ) {
        ExprInserter.depth += 1;

        String nextType = type;
        String ret = null;

        // Occasionally cast to a different basic numeric type
        if (ExprInserter.isNumType(type) && RandomChooser.nextInt(10) < 3) {
            nextType = ExprInserter.getRandomBasicNumType(type);
            if (!stmtPicker.hasMutator(nextType)) {
                nextType = type; // revert if no mutators for the new type
            }
        }
        
        // Try to get a complex mutator first
        if (!stmtPicker.hasMutator(nextType) || ExprInserter.depth > 2 || RandomChooser.nextInt(10) < 3) {
            // If no complex mutator available or depth exceeded, return a simple expression
            ret = ExprInserter.getSimpleExpr(nextType, className, fnName);
        }
        else {
            // Select a random mutator unit
            MutatorUnit mu = stmtPicker.nextMutator(nextType);
            result.addImports(mu.getImports());
            result.addFuncs(mu.getNeedFuncs());
            result.addClazzs(mu.getNeedClass());
            
            if (mu.getMutType() == MutType.FUNCTION) {
                List<String> paramList = new ArrayList<>();
                for (String paramType: mu.getParamTypes()) {
                    paramList.add(
                        ExprInserter.insert(
                            paramType, className, fnName,
                            result, stmtPicker
                        )
                    );
                }
                ret = mu.getMutString(paramList);
                Logger.debug("Generated function-level mutator: " + ret);
            }
            else {
                Map<String, String> newGbVars = new java.util.HashMap<>();
                Map<String, String> newLocalVars = new java.util.HashMap<>();
                Map<String, String> newExprs = new java.util.HashMap<>();
                Map<String, String> newConsts = new java.util.HashMap<>();
                
                for (Map.Entry<String, String> entry : mu.getGBVars().entrySet()) {
                    newGbVars.put(entry.getKey(), VarPool.getGBVar(entry.getValue()));
                }
                for (Map.Entry<String, String> entry : mu.getLocalVars().entrySet()) {
                    newLocalVars.put(entry.getKey(),
                        ExprInserter.getLocalVar(className, fnName, entry.getValue()));
                }
                for (Map.Entry<String, String> entry : mu.getExprs().entrySet()) {
                    newExprs.put(entry.getKey(), ExprInserter.insert(entry.getValue(), className, fnName, result, stmtPicker));
                }
                for (Map.Entry<String, String> entry : mu.getConsts().entrySet()) {
                    newConsts.put(entry.getKey(), ExprInserter.getRandomConst(entry.getValue()));
                }
                ret = mu.getMutString(newGbVars, newLocalVars, newExprs, newConsts, null);
            }
        }
        ret = ExprInserter.transExprType(type, nextType, ret);
                
        ExprInserter.depth -= 1;
        return ret;
    }

}
