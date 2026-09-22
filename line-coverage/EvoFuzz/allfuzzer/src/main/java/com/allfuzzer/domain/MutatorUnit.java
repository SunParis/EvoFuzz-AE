package com.allfuzzer.domain;

import java.util.Map;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Comparator;
import java.util.stream.Collectors;

import com.allfuzzer.config.Config;
import com.allfuzzer.util.Logger;
import com.allfuzzer.util.RandomChooser;

/**
 * MutatorUnit represents a mutator extracted from source code.
 */
public class MutatorUnit {

    public enum MutType {
        CLASS,
        FUNCTION,
        STATEMENT,
        NEW,
        INIT,
        CHECK
    }

    private String srcFilePath;

    private MutType mutType;

    public static class Func {
        public String name;
        public List<String> paramTypes = new ArrayList<>();
        public List<String> paramNames = new ArrayList<>();
        public String funcStr;
    }
    
    private Func funcInfo = null;

    public String returnType;

    // name -> type
    private Map<String, String> gbVars = new LinkedHashMap<>();
    
    // name -> type
    private Map<String, String> localVars = new LinkedHashMap<>();
    
    // name -> type
    private Map<String, String> exprs = new LinkedHashMap<>();

    // name -> type
    private Map<String, String> consts = new LinkedHashMap<>();

    // unique name -> MutatorUnit
    private Map<String, MutatorUnit> needFuncs = new LinkedHashMap<>();

    // unique name -> MutatorUnit
    private Map<String, MutatorUnit> needClass = new LinkedHashMap<>();
    
    private Set<String> requiredImports = new HashSet<>();

    private String mutString;

    private int priority = 50; // default priority

    /**
     * Set the source file path.
     * @param path the source file path
     */
    public void setSrcFilePath(String path) {
        this.srcFilePath = path;
    }

    /**
     * Get the source file path.
     * @return the source file path
     */
    public String getSrcFilePath() {
        return this.srcFilePath;
    }

    /**
     * Set the mutator type.
     * @param mutType the mutator type
     */
    public void setMutType(MutType mutType) {
        this.mutType = mutType;
    }

    /**
     * Get the mutator type.
     * @return the mutator type
     */
    public MutType getMutType() {
        return this.mutType;
    }

    /**
     * Add multiple global variables.
     * @param gbvarMap map of global variable names to types
     */
    public void setGBVars(Map<String, String> gbvarMap) {
        this.gbVars.putAll(gbvarMap);
    }

    /**
     * Get global variables.
     * @return map of global variable names to types
     */
    public Map<String, String> getGBVars() {
        return this.gbVars;
    }

    /**
     * Add multiple local variables.
     * @param localVarMap map of local variable names to types
     */
    public void setLocalVars(Map<String, String> localVarMap) {
        this.localVars.putAll(localVarMap);
    }

    /**
     * Get local variables.
     * @return map of local variable names to types
     */
    public Map<String, String> getLocalVars() {
        return this.localVars;
    }

    /**
     * Add multiple expression variables.
     * @param exprMap map of expression variable names to types
     */
    public void setExprs(Map<String, String> exprMap) {
        this.exprs.putAll(exprMap);
    }

    /**
     * Get expression variables.
     * @return map of expression variable names to types
     */
    public Map<String, String> getExprs() {
        return this.exprs;
    }

    /**
     * Add multiple constant variables.
     * @param constMap map of constant variable names to types
     */
    public void setConsts(Map<String, String> constMap) {
        this.consts.putAll(constMap);
    }

    /**
     * Get constant variables.
     * @return map of constant variable names to types
     */
    public Map<String, String> getConsts() {
        return this.consts;
    }

    /**
     * Add required imports.
     * @param imports set of import strings
     */
    public void setImports(Set<String> imports) {
        this.requiredImports = imports;
    }

    /**
     * Get required imports.
     * @return set of required import strings
     */
    public Set<String> getImports() {
        return this.requiredImports;
    }

    /**
     * Set need functions.
     * @param mus map of unique function names to MutatorUnits
     */
    public void setNeedFuncs(Map<String, MutatorUnit> mus) {
        this.needFuncs = mus;
    }

    /**
     * Get need functions.
     * @return map of unique function names to MutatorUnits
     */
    public Map<String, MutatorUnit> getNeedFuncs() {
        return this.needFuncs;
    }

    /**
     * Set need classes.
     * @param mus map of unique class names to MutatorUnits
     */
    public void setNeedClass(Map<String, MutatorUnit> mus) {
        this.needClass = mus;
    }

    /**
     * Get need classes.
     * @return map of unique class names to MutatorUnits
     */
    public Map<String, MutatorUnit> getNeedClass() {
        return this.needClass;
    }

    /**
     * Set the return type of the mutator.
     * @param retType the return type
     */
    public void setRetType(String retType) {
        this.returnType = retType;
    }

    /**
     * Get the return type of the mutator.
     * @return the return type
     */
    public String getRetType() {
        return this.returnType;
    }

    /**
     * Set the priority of the mutator.
     * @param priority the priority
     */
    public void setPriority(int priority) {
        if (priority < 1 || priority > 100) {
            this.priority = 50;
        } else {
            this.priority = priority;
        }
        Logger.debug("MutatorUnit priority set to " + this.priority);
    }

    /**
     * Get the priority of the mutator.
     * @return the priority
     */
    public int getPriority() {
        return this.priority;
    }

    /**
     * Set the mutator string.
     * @param mutString the mutator string
     */
    public void setMutString(String mutString) {
        if (mutString == null || mutString.isEmpty()) {
            throw new IllegalArgumentException("Mutator string cannot be null or empty.");
        }
        this.mutString = mutString;
    }

    /**
     * Set function information.
     * @param name function name
     * @param paramTypes list of parameter types
     * @param paramNames list of parameter names
     * @param funcStr function string
     */
    public void setFuncInfo(String name, List<String> paramTypes, List<String> paramNames, String funcStr) {
        this.funcInfo = new Func();
        this.funcInfo.name = name;
        this.funcInfo.paramTypes = paramTypes;
        this.funcInfo.paramNames = paramNames;
        this.funcInfo.funcStr = funcStr;
        if (this.funcInfo.funcStr.endsWith("};\n")) {
            this.funcInfo.funcStr = this.funcInfo.funcStr.substring(0, this.funcInfo.funcStr.length() - 2);
        }
        else if (this.funcInfo.funcStr.endsWith("};")) {
            this.funcInfo.funcStr = this.funcInfo.funcStr.substring(0, this.funcInfo.funcStr.length() - 1);
        }
    }

    /**
     * Get function parameter types.
     * @return list of parameter types
     */
    public List<String> getParamTypes() {
        if (this.funcInfo == null) {
            return new ArrayList<>();
        }
        return this.funcInfo.paramTypes;
    }

    /**
     * Get function parameter names.
     * @param gbmap map of global variable names to new names
     * @return function string
     */
    public String getFuncStr(Map<String, String> gbmap) {
        if (this.funcInfo == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("public static " + this.returnType + " " + this.funcInfo.name + "(");
        for (int i = 0; i < this.funcInfo.paramTypes.size(); i++) {
            sb.append(this.funcInfo.paramTypes.get(i) + " " + this.funcInfo.paramNames.get(i));
            if (i != this.funcInfo.paramTypes.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append(") {\n");
        String tmp = this.funcInfo.funcStr;
        for (Map.Entry<String, String> entry : this.gbVars.entrySet()) {
            String oldName = entry.getKey();
            String newName = gbmap.get(oldName);
            if (newName != null) {
                tmp = tmp.replace(oldName, newName);
            }
        }
        sb.append(tmp + "\n}");
        return sb.toString();
    }

    /**
     * Get the mutator string.
     * @return the mutator string
     */
    public String getMutString() {
        if (this.mutType != MutType.NEW && this.mutType != MutType.CLASS) {
            throw new UnsupportedOperationException("MutatorUnit.replaceVars(String) is only supported for NEW and CLASS mutator types.");
        }
        String result = this.replaceTmp(this.mutString);
        return result;
    }

    /**
     * Get the mutator string with local variables replaced.
     * @param targetVar the target variable name to replace local variables with
     * @return the mutator string with local variables replaced
     */
    public String getMutString(String targetVar) {
        if (this.mutType != MutType.INIT && this.mutType != MutType.CHECK) {
            throw new UnsupportedOperationException("MutatorUnit.replaceVars(String) is only supported for INIT and CHECK mutator types.");
        }
        String result = this.mutString;
        for (Map.Entry<String, String> entry : this.localVars.entrySet()) {
            String oldName = entry.getKey();
            if (targetVar != null) {
                result = result.replace(oldName, targetVar);
            }
            Logger.debug("=======\n" + "[" + oldName + "] replaced with [" + targetVar + "]");
        }
        result = this.replaceTmp(result);
        Logger.debug(this.localVars.size() + " local vars replaced in INIT/CHECK mutator.");
        return result;
    }

    /**
     * Get the mutator string for function calls.
     * @param params list of parameter names
     * @return the mutator string for function calls
     */
    public String getMutString(List<String> params) {
        if (this.mutType != MutType.FUNCTION) {
            throw new UnsupportedOperationException("MutatorUnit.replaceVars(String) is only supported for FUNCTION mutator types.");
        }
        if (this.funcInfo == null || params.size() != this.funcInfo.paramTypes.size()) {
            Logger.error("MutatorUnit.getMutString: funcInfo is null.");
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(Config.GbClsName + "." + this.funcInfo.name + "(");
        for (String paramName : params) {
            sb.append(paramName + ", ");
        }
        if (params.size() > 0) {
            sb.setLength(sb.length() - 2); // Remove last ", "
        }
        sb.append(")");
        return sb.toString();
    }

    /**
     * Get the mutator string with variables replaced.
     * @param gbmap global variable map
     * @param localmap local variable map
     * @param exprmap expressions map
     * @param constmap constants map
     * @param stmtslist list of statements to replace
     * @return the mutator string with variables replaced
     */
    public String getMutString(Map<String, String> gbmap, Map<String, String> localmap,
        Map<String, String> exprmap, Map<String, String> constmap, List<String> stmtslist
    ) {
        String result = this.mutString;
        
        List<String> sortedKeys = this.gbVars.keySet()
            .stream()
            .sorted(Comparator.comparingInt(String::length).reversed())
            .collect(Collectors.toList());
        for (String oldName: sortedKeys) {
            String newName = gbmap.get(oldName);
            if (newName != null) {
                result = result.replace(oldName, newName);
            }
        }
        
        sortedKeys = this.localVars.keySet()
            .stream()
            .sorted(Comparator.comparingInt(String::length).reversed())
            .collect(Collectors.toList());
        for (String oldName: sortedKeys) {
            String newName = localmap.get(oldName);
            if (newName != null) {
                result = result.replace(oldName, newName);
            }
        }

        sortedKeys = this.exprs.keySet()
            .stream()
            .sorted(Comparator.comparingInt(String::length).reversed())
            .collect(Collectors.toList());
        for (String oldName: sortedKeys) {
            String newName = exprmap.get(oldName);
            if (newName != null) {
                result = result.replace(oldName, newName);
            }
        }

        sortedKeys = this.consts.keySet()
            .stream()
            .sorted(Comparator.comparingInt(String::length).reversed())
            .collect(Collectors.toList());
        for (String oldName: sortedKeys) {
            String newName = constmap.get(oldName);
            if (newName != null) {
                result = result.replace(oldName, newName);
            }
        }

        result = this.replactStmts(result, stmtslist);
        result = this.replaceTmp(result);
        if (this.returnType.equals("void") && !result.endsWith(";")) {
            result += ";";
        }

        return result;
    }

    /**
     * Get the number of statements in the mutator string.
     * @return the number of statements
     */
    public int getStmtNum() {
        if (this.mutString == null || this.mutString.isEmpty()) {
            return 0;
        }
        String target = "$stmt()";
        int count = 0;
        int index = 0;
        while ((index = this.mutString.indexOf(target, index)) != -1) {
            count++;
            index += target.length();
        }
        return count;
    }

    private String replactStmts(String input, List<String> stmtsList) {
        if (stmtsList == null || stmtsList.size() == 0) {
            return input;
        }
        String target = "$stmt()";
        String result = input;
        for (String stmt : stmtsList) {
            int index = result.indexOf(target);
            if (index == -1) {
                break;
            }
            result = result.substring(0, index) + stmt + result.substring(index + target.length());
        }
        if (result.contains(target)) {
            result = result.replace(target, "if (false) { System.out.println(\"Ciallo～(∠·ω< )⌒★\"); }");
        }
        return result;
    }

    private String replaceTmp(String input) {
        String result = input;
        while (result.contains("$tmp")) {
            int index = result.indexOf("$tmp") + "$tmp".length();
            StringBuilder sb = new StringBuilder();
            sb.append("$tmp");
            while (Character.isJavaIdentifierPart(result.charAt(index))) {
                char c = result.charAt(index);
                sb.append(c);
                index++;
            }
            String newName = "TmpVar_" + System.nanoTime() + "_" + 
                System.currentTimeMillis() + "_" + RandomChooser.randomString(2);
            result = result.replace(sb.toString(), newName);
        }
        return result;
    }

}
