package com.allfuzzer.inserter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.allfuzzer.config.Config;
import com.allfuzzer.util.Logger;
import com.allfuzzer.util.RandomChooser;

/**
 * VarPool manages the pool of variable names to avoid naming conflicts
 *  when inserting mutator expressions into the host code.
 */
public class VarPool {
    
    private static Map<String, Map<String, Map<String, Set<String>>>> localVars = new HashMap<>();
    private static Map<String, Set<String>> gbVars = new HashMap<>();

    /**
     * Create a new, unique variable name.
     * @param type The variable type.
     * @param isGB Whether it is a global variable.
     * @return The generated variable name.
     */
    private static String createName(String type, boolean isGB) {
        StringBuilder sb = new StringBuilder();
        if (isGB) {
            sb.append("gb_");
        } else {
            sb.append("lv_");
        }
        for (char c : type.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                sb.append(c);
            } 
            else if (c == '[') {
                sb.append("_arr");
                break;
            }
            else {
                sb.append('_');
            }
        }
        return sb.toString() + "_" + System.currentTimeMillis() + "_" + System.nanoTime();
    }

    /**
     * Get a local variable name of the given type.
     * @param clzzname The class name.
     * @param methodname The method name.
     * @param type The variable type.
     * @return The variable name.
     */
    public static String getLocalVar(String clzzname, String methodname, String type) {
        Set<String> names = null;
        if (!localVars.containsKey(clzzname)) {
            localVars.put(clzzname, new HashMap<>());
        }
        if (!localVars.get(clzzname).containsKey(methodname)) {
            localVars.get(clzzname).put(methodname, new HashMap<>());
        }
        if (!localVars.get(clzzname).get(methodname).containsKey(type)) {
            names = new HashSet<>();
            localVars.get(clzzname).get(methodname).put(type, names);
        }        
        if (names == null) {
            names = localVars.get(clzzname).get(methodname).get(type);
        }

        int idx = names.size() >= 5 ? RandomChooser.nextInt(names.size()): RandomChooser.nextInt(names.size() + 1);
        if (idx == names.size()) {
            String newName = VarPool.createName(type, false) + names.size();
            names.add(newName);
            return newName;
        }
        return names.toArray()[idx].toString();
    }

    /**
     * Get a global variable name of the given type.
     * @param type The variable type.
     * @return The variable name.
     */
    public static String getGBVar(String type) {
        Set<String> names = gbVars.get(type);
        if (names == null) {
            Set<String> newList = new HashSet<>();
            gbVars.put(type, newList);
            newList.add(VarPool.createName(type, true) + "0");
            return Config.GbClsName + "." + newList.toArray()[0].toString();
        }
        else {
            int idx = names.size() >= 5 ? RandomChooser.nextInt(names.size()): RandomChooser.nextInt(names.size() + 1);
            if (idx == names.size()) {
                String newName = VarPool.createName(type, true) + names.size();
                names.add(newName);
                return Config.GbClsName + "." + newName;
            }
            return Config.GbClsName + "." + names.toArray()[idx].toString();
        }
    }

    /**
     * Get a constant of the given type.
     * @param type The desired type of the constant.
     * @return A string representing a constant of the given type.
     */
    public static String getConst(String type) {
        // Arrays are not supported as constants
        if (type.endsWith("[]")) {
            return "null";
        }
        String ret;
        switch (type) {
            case "int":
                ret = String.valueOf(RandomChooser.nextInt(1000) + 1);
                if (RandomChooser.nextInt(10) < 1) {
                    ret = "-" + ret;
                }
                if (RandomChooser.nextInt(50) < 1) {
                    ret = "Integer.MAX_VALUE";
                }
                else if (RandomChooser.nextInt(50) < 1) {
                    ret = "Integer.MIN_VALUE";
                }
                break;
            case "Integer":
                ret = String.format("Integer.valueOf(%s)", VarPool.getConst("int"));
                break;
            case "long":
                ret = String.valueOf(RandomChooser.nextInt(100000) + 1) + "L";
                if (RandomChooser.nextInt(10) < 1) {
                    ret = "-" + ret;
                }
                if (RandomChooser.nextInt(50) < 1) {
                    ret = "Long.MAX_VALUE";
                }
                else if (RandomChooser.nextInt(50) < 1) {
                    ret = "Long.MIN_VALUE";
                }
                break;
            case "Long":
                ret = String.format("Long.valueOf(%s)", VarPool.getConst("long"));
                break;
            case "float":
                ret = String.valueOf(RandomChooser.nextFloat() * 1000 + 1) + "f";
                if (RandomChooser.nextInt(10) < 1) {
                    ret = "-" + ret;
                }
                if (RandomChooser.nextInt(50) < 1) {
                    ret = "Float.NaN";
                }
                else if (RandomChooser.nextInt(50) < 1) {
                    ret = "Float.MIN_VALUE";
                }
                break;
            case "Float":
                ret = String.format("Float.valueOf(%s)", VarPool.getConst("float"));;
                break;
            case "double":
                ret = String.valueOf(RandomChooser.nextDouble() * 10000 + 1);
                if (RandomChooser.nextInt(10) < 1) {
                    ret = "-" + ret;
                }
                if (RandomChooser.nextInt(50) < 1) {
                    ret = "Double.NaN";
                }
                else if (RandomChooser.nextInt(50) < 1) {
                    ret = "Double.MIN_VALUE";
                }
                break;
            case "Double":
                ret = String.format("Double.valueOf(%s)", VarPool.getConst("double"));
                break;
            case "boolean":
                ret = RandomChooser.nextBoolean() ? "true" : "false";
                break;
            case "Boolean":
                ret = RandomChooser.nextBoolean() ? "Boolean.TRUE" : "Boolean.FALSE";
                break;
            case "char":
                ret = "(char)" + String.valueOf(RandomChooser.nextInt(100) + 1);
                break;
            case "Character":
                ret = String.format("Character.valueOf(%s)", VarPool.getConst("char"));
                break;
            case "byte":
                ret = "(byte)" + String.valueOf(RandomChooser.nextInt(100) + 1);
                break;
            case "Byte":
                ret = String.format("Byte.valueOf(%s)", VarPool.getConst("byte"));
                break;
            case "short":
                ret = "(short)" + String.valueOf(RandomChooser.nextInt(1000) + 1);
                break;
            case "Short":
                ret = String.format("Short.valueOf(%s)", VarPool.getConst("short"));
                break;
            case "String":
                if (RandomChooser.nextInt(10) < 1) {
                    ret = "\"\"";
                } else {
                    ret = "\"" + RandomChooser.randomString() + "\"";
                }
                break;
            case "IntVector":
                ret = "null";
                break;
            case "ByteVector":
                ret = "null";
                break;
            case "ShortVector":
                ret = "null";
                break;
            case "FloatVector":
                ret = "null";
                break;
            case "Object":
                ret = "new Object()";
                break;
            default:
                ret = "null";
                Logger.warning("Unsupported const type: " + type + ", using null as default.");
                break;
        }
        return ret;
    }

    /**
     * Get a small constant of the given type.
     * @param type The desired type of the constant.
     * @param small Whether to generate a small constant.
     * @return A string representing a small constant of the given type.
     */
    public static String getConst(String type, boolean small) {
        if (!small) {
            return getConst(type);
        }
        // Arrays are not supported as constants
        if (type.endsWith("[]")) {
            return "null";
        }
        String ret;
        int max = (int)((Config.getArraySize() - 32) / 2.2);
        switch (type) {
            case "int":
                ret = String.valueOf(RandomChooser.nextInt(max) + 1);
                break;
            case "Integer":
                ret = String.format("Integer.valueOf(%s)", VarPool.getConst("int"));
                break;
            case "long":
                ret = String.valueOf(RandomChooser.nextInt(max) + 1) + "L";
                break;
            case "Long":
                ret = String.format("Long.valueOf(%s)", VarPool.getConst("long", true));
                break;
            case "float":
                ret = String.valueOf(RandomChooser.nextFloat() + 1) + "f";
                break;
            case "Float":
                ret = String.format("Float.valueOf(%s)", VarPool.getConst("float", true));;
                break;
            case "double":
                ret = String.valueOf(RandomChooser.nextDouble() + 1);
                break;
            case "Double":
                ret = String.format("Double.valueOf(%s)", VarPool.getConst("double", true));
                break;
            case "boolean":
                ret = RandomChooser.nextBoolean() ? "true" : "false";
                break;
            case "Boolean":
                ret = RandomChooser.nextBoolean() ? "Boolean.TRUE" : "Boolean.FALSE";
                break;
            case "char":
                ret = "(char)" + String.valueOf(RandomChooser.nextInt(26) + 'a');
                break;
            case "Character":
                ret = String.format("Character.valueOf(%s)", VarPool.getConst("char", true));
                break;
            case "byte":
                ret = "(byte)" + String.valueOf(RandomChooser.nextInt(max) + 1);
                break;
            case "Byte":
                ret = String.format("Byte.valueOf(%s)", VarPool.getConst("byte", true));
                break;
            case "short":
                ret = "(short)" + String.valueOf(RandomChooser.nextInt(max) + 1);
                break;
            case "Short":
                ret = String.format("Short.valueOf(%s)", VarPool.getConst("short", true));
                break;
            case "String":
                ret = "\"" + RandomChooser.randomString(5) + "\"";
                break;
            case "IntVector":
                ret = "null";
                break;
            case "ByteVector":
                ret = "null";
                break;
            case "ShortVector":
                ret = "null";
                break;
            case "FloatVector":
                ret = "null";
                break;
            case "Object":
                ret = "new Object()";
                break;
            default:
                ret = "null";
                Logger.warning("Unsupported const type: " + type + ", using null as default.");
                break;
        }
        return ret;
    }

    public static boolean isBasicType(String type) {
        switch (type) {
            case "int":
            case "long":
            case "short":
            case "byte":
            case "float":
            case "double":
            case "char":
            case "boolean":
            case "String":
            case "Integer":
            case "Long":
            case "Short":
            case "Byte":
            case "Float":
            case "Double":
            case "Character":
            case "Boolean":
                return true;
            default:
                return false;
        }
    }
    
    /**
     * Get the local variable pool.
     * @return map of type to set of local variable names (VarType -> Set<VarName>)
     */
    public static Map<String, Map<String, Map<String, Set<String>>>> getLocalVars() {
        return VarPool.localVars;
    }

    public static Map<String, Set<String>> getLocalVarsTypMap() {
        Map<String, Set<String>> ret = new HashMap<>();
        for (String clzzname : localVars.keySet()) {
            for (String methodname : localVars.get(clzzname).keySet()) {
                for (String type : localVars.get(clzzname).get(methodname).keySet()) {
                    if (!ret.containsKey(type)) {
                        ret.put(type, new HashSet<>());
                    }
                    Set<String> sset = ret.get(type);
                    for (String varName: localVars.get(clzzname).get(methodname).get(type)) {
                        sset.add(clzzname + "$$" + methodname + "$$" + varName);
                    }
                }
            }
        }
        return ret;
    }

    /**
     * Get the global variable pool.
     * @return map of type to set of global variable names (VarType -> Set<VarName>)
     */
    public static Map<String, Set<String>> getGBVars() {
        return VarPool.gbVars;
    }

    public static String to_string() {
        StringBuilder sb = new StringBuilder();
        sb.append("Global Variables:\n");
        for (String type : gbVars.keySet()) {
            sb.append("  ").append(type).append(": ").append(gbVars.get(type)).append("\n");
        }
        sb.append("\nLocal Variables:\n");
        for (String clzzname : localVars.keySet()) {
            sb.append("Class ").append(clzzname).append(":\n");
            for (String methodname : localVars.get(clzzname).keySet()) {
                sb.append("  Method ").append(methodname).append(":\n");
                for (String type : localVars.get(clzzname).get(methodname).keySet()) {
                    sb.append("    ").append(type).append(": ").append(localVars.get(clzzname).get(methodname).get(type)).append("\n");
                }
            }
        }
        return sb.toString();
    }

}
