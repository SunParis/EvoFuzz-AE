package com.allfuzzer.spoon;

import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import spoon.reflect.code.*;
import spoon.reflect.declaration.*;
import spoon.reflect.reference.CtTypeReference;

import com.allfuzzer.util.Logger;
import com.allfuzzer.util.RandomChooser;

public class SpoonCode {
    
    /**
     * An abstraction for Spoon objects.
     */
    public interface SpoonObj {}
    
    /**
     * An abstraction for a Spoon CtClass.
     */
    public static class SpoonCls implements SpoonObj {
        private CtClass<?> ctclass;
        private Set<SpoonField> fields = null;
        
        public SpoonCls(CtClass<?> ctclass) {
            this.ctclass = ctclass;
        }
        
        /**
         * Return the underlying CtClass object.
         * @return CtClass object
         */
        public CtClass<?> data() {
            return this.ctclass;
        }

        /**
         * Set the class SIMPLE name.
         * @param name class SIMPLE name
         */
        public void setSimpleName(String name) {
            this.ctclass.setSimpleName(name);
        }

        /**
         * Get the class SIMPLE name.
         * @return class SIMPLE name
         */
        public String getSimpleName() {
            return this.ctclass.getSimpleName();
        }

        /**
         * Get all fields in the class.
         * @return set of SpoonField objects
         */
        public Set<SpoonField> getFields() {
            if (this.fields != null)    return this.fields;
            this.fields = this.ctclass.getFields().stream()
                .filter(method -> method.getDeclaringType().equals(this.data()))
                .map(field -> new SpoonField(field))
                .collect(Collectors.toSet());
            return this.fields;
        }

        /**
         * Get the main method in the class.
         * @return SpoonMethod object for main method,
         *      or null if not found
         */
        public SpoonMethod getMain() {
            CtMethod<?> mainMethod = ctclass.getMethods().stream()
                .filter(m -> "main".equals(m.getSimpleName()))
                .findFirst()
                .orElse(null);
            return new SpoonMethod(mainMethod);
        }

        /**
         * Get the constructors in the class.
         * @return list of SpoonMethod objects for constructors,
         *      or null if none found
         */
        public List<SpoonMethod> getConstructors() {
            List<SpoonMethod> ret = ctclass.getMethods().stream()
                .filter(m -> ctclass.getSimpleName().equals(m.getSimpleName()))
                .map(SpoonMethod::new)
                .collect(Collectors.toList());
            if (ret.isEmpty()) {
                return null;
            }
            return ret;
        }

    };

    /**
     * An abstraction for a Spoon CtInterface.
     */
    public static class SpoonInterFace implements SpoonObj {
        private CtInterface<?> ctinterface;

        public SpoonInterFace(CtInterface<?> ctinterface) {
            this.ctinterface = ctinterface;
        }

        /**
         * Return the underlying CtInterface object.
         * @return CtInterface object
         */
        public CtInterface<?> data() {
            return this.ctinterface;
        }

        /**
         * Get the simple name of the interface.
         * @return simple interface name
         */
        public String getSimpleName() {
            return this.ctinterface.getSimpleName();
        }

        /**
         * Set the simple name of the interface.
         * @param name simple interface name
         */
        public void setSimpleName(String name) {
            this.ctinterface.setSimpleName(name);
        }
    };

    /**
     * An abstraction for a Spoon CtParameter.
     */
    public static class SpoonParameter implements SpoonObj {
        private CtParameter<?> ctparam;

        public SpoonParameter(CtParameter<?> ctparam) {
            this.ctparam = ctparam;
        }

        /**
         * Return the underlying CtParameter object.
         * @return CtParameter object
         */
        public CtParameter<?> data() {
            return this.ctparam;
        }

        /**
         * Get the simple name of the parameter.
         * @return simple parameter name
         */
        public String getSimpleName() {
            return this.ctparam.getSimpleName();
        }

        /**
         * Get the type of the parameter.
         * @return parameter type as a string
         */
        public String getType() {
            return this.ctparam.getType().toString();
        }
    };

    /**
     * An abstraction for a Spoon CtMethod.
     */
    public static class SpoonMethod implements SpoonObj {
        private CtMethod<?> ctmethod;
        private List<SpoonParameter> paramMap = null;
        private String simpleName = null;

        public SpoonMethod(CtMethod<?> ctmethod) {
            this.ctmethod = ctmethod;
        }

        public SpoonCls getParentCls() {
            CtType<?> parent = this.ctmethod.getParent(
                spoon.reflect.declaration.CtType.class);
            if (parent instanceof CtClass<?>) {
                return new SpoonCls((CtClass<?>) parent);
            }
            return null;
        }

        /**
         * Return the underlying CtMethod object.
         * @return CtMethod object
         */
        public CtMethod<?> data() {
            return this.ctmethod;
        }

        /**
         * Get the statements in the method body as a string.
         * @return method body statements
         */
        public String getStmts() {
            StringBuilder sb = new StringBuilder();
            CtBlock<?> body = this.ctmethod.getBody();
            if (body != null) {
                for (CtStatement stmt : body.getStatements()) {
                    sb.append(stmt.toString());
                    sb.append(";\n");
                }
            }
            return sb.toString();
        }

        /**
         * Set the simple name of the method.
         * @param name simple method name
         */
        public void setSimpleName(String name) {
            this.ctmethod.setSimpleName(name);
            this.simpleName = name;
        }

        /**
         * Get the simple name of the method.
         * @return simple method name
         */
        public String getSimpleName() {
            if (this.simpleName == null) {
                this.simpleName = this.ctmethod.getSimpleName();
            }
            return this.simpleName;
        }

        /**getSimpleName
         * Get a unique name for the method (including signature).
         * @return unique method name
         */
        public String getUniqueName() {
            return this.ctmethod.getSignature();
        }

        /**
         * Get method parameters as a map of parameter name to type.
         * @return map of parameter names to types
         */
        public List<SpoonParameter> getParams() {
            if (this.paramMap != null)    return this.paramMap;
            this.paramMap = new ArrayList<>();
            for (CtParameter<?> param: this.ctmethod.getParameters()) {
                this.paramMap.add(new SpoonParameter(param));
            }
            return this.paramMap;
        }

        /**
         * Get method parameter types as a list of strings.
         * @return list of parameter types
         */
        public List<String> getParamTypes() {
            List<String> paramTypes = new ArrayList<>();
            for (CtParameter<?> param: this.ctmethod.getParameters()) {
                paramTypes.add(param.getType().toString());
            }
            return paramTypes;
        }

        /**
         * Get method parameter names as a list of strings.
         * @return list of parameter names
         */
        public List<String> getParamNames() {
            List<String> paramTypes = new ArrayList<>();
            for (CtParameter<?> param: this.ctmethod.getParameters()) {
                paramTypes.add(param.getSimpleName());
            }
            return paramTypes;
        }

        /**
         * Get the return type of the method.
         * @return return type as a string
         */
        public String getRetType() {
            return this.ctmethod.getType() == null ? "void"
                : this.ctmethod.getType().toString();
        }
        
        /**
         * Get a random variable name of the specified type within the method.
         * @param typeName the desired variable type name
         * @return a random variable name of the specified type, or null if none found
         */
        public String getRandomVariableOfType(String typeName) {
            if (this.ctmethod == null || typeName == null || typeName.endsWith("[]") || this.ctmethod.getBody() == null) {
                return null;
            }
            
            List<CtVariable<?>> matchingVariables = new ArrayList<>();
            
            // Check parameters
            for (CtParameter<?> param : this.ctmethod.getParameters()) {
                if (isMatchingType(param.getType(), typeName)
                    && !param.hasModifier(ModifierKind.FINAL)
                ) {
                    matchingVariables.add(param);
                }
            }
            
            // Check local variables
            CtBlock<?> methodBody = this.ctmethod.getBody();
            for (CtStatement childStmt: methodBody.getStatements()) {
                if (childStmt instanceof CtLocalVariable) {
                    CtLocalVariable<?> localVar = (CtLocalVariable<?>) childStmt;
                    if (isMatchingType(localVar.getType(), typeName)
                        && !localVar.hasModifier(ModifierKind.FINAL)
                    ) {
                        matchingVariables.add(localVar);
                    }
                }
                else {
                    // Stop searching further statements
                    break;
                }
            }
            
            // If no matching variables found, return null
            if (matchingVariables.isEmpty()) {
                return null;
            }
            
            // Select a random variable from the matching ones
            return matchingVariables.get(RandomChooser.nextInt(matchingVariables.size())).getSimpleName();
        }

        private static boolean isMatchingType(CtTypeReference<?> typeRef, String targetTypeName) {
            if (typeRef == null) {
                return false;
            }
            
            // Check for generic type arguments
            if (typeRef.getActualTypeArguments() != null && !typeRef.getActualTypeArguments().isEmpty()) {
                return false;
            }
            
            // Get simple and qualified type names
            String simpleTypeName = typeRef.getSimpleName();
            String qualifiedTypeName = typeRef.getQualifiedName();
            
            // Check if it matches the target type name
            return targetTypeName.equals(simpleTypeName) || targetTypeName.equals(qualifiedTypeName);
        }

        /**
         * Get the method body as a SpoonBlock.
         * @return SpoonBlock representing the method body
         */
        public SpoonBlock getBlock() {
            return new SpoonBlock(this.ctmethod.getBody());
        }

        /**
         * Add statements to the front of the method body.
         * @param stmt statements to add
         */
        public void addStmtsToFront(CtCodeSnippetStatement stmt) {
            CtBlock<?> body = this.ctmethod.getBody();
            if (body != null) {
                body.addStatement(0, stmt);
            }
        }

        /**
         * Add statements to the "fake" front of the method body,
         * @param stmt statements to add
         *      i.e., after variable declarations and initializations
         */
        public void addStmtsToFakeFront(CtCodeSnippetStatement stmt) {
            CtBlock<?> body = this.ctmethod.getBody();
            int start_idx = body.getStatements().size();
            for (int idx = body.getStatements().size() - 1; idx >= 0; idx--) {
                if ((body.getStatements().get(idx) instanceof CtLocalVariable) ||
                    (body.getStatements().get(idx).toString().contains("FuzzerUtils.init"))
                ) {
                    break;
                }
                start_idx = idx + 1;
            }
            if (body != null) {
                body.addStatement(start_idx, stmt);
            }
        }

        /**
         * Add statements to the end of the method body,
         * @param stmt statements to add
         */
        public void addStmtsToEnd(CtCodeSnippetStatement stmt) {
            CtBlock<?> body = this.ctmethod.getBody();
            if (body != null) {
                int insert_idx = body.getStatements().size();
                if (body.getStatements().get(insert_idx - 1) instanceof CtReturn<?>
                    || body.getStatements().get(insert_idx - 1) instanceof CtThrow
                ) {
                    insert_idx -= 1;
                }
                body.addStatement(insert_idx, stmt);
            }
        }
        
    };

    /**
     * An abstraction for a Spoon CtField.
     */
    public static class SpoonField implements SpoonObj {
        private CtField<?> ctfield;

        public SpoonField(CtField<?> ctfield) {
            this.ctfield = ctfield;
        }

        public CtField<?> data() {
            return this.ctfield;
        }

        /**
         * Get the simple name of the field.
         * @return simple field name
         */
        public String getSimpleName() {
            return this.ctfield.getSimpleName();
        }

        /**
         * Get the type of the field.
         * @return field type as a string
         */
        public String getType() {
            return this.ctfield.getType().toString();
        }

        /**
         * Get the initial integer value of the field, if any.
         * @return initial integer value, or null if not applicable
         */
        public Integer getInitialIntOrNull() {
            if (this.ctfield == null) return null;

            // Only accept primitive int fields
            if (this.ctfield.getType() == null
                || !this.ctfield.getType().isPrimitive()
                || !"int".equals(this.ctfield.getType().getSimpleName())
            ) {
                return null;
            }

            // Only accept initializer at declaration site (e.g., "int x = 3;")
            CtExpression<?> expr = this.ctfield.getDefaultExpression(); // older Spoon: getAssignment()
            if (!(expr instanceof CtLiteral<?>)) return null;

            // Parameterize CtLiteral to avoid raw type
            Object v = ((CtLiteral<?>) expr).getValue();
            return (v instanceof Integer) ? (Integer) v : null;
        }
    }

    /**
     * An abstraction for a Spoon CtLoop.
     */
    public static class SpoonLoop implements SpoonObj {
        private CtLoop ctloop;

        public SpoonLoop(CtLoop ctloop) {
            this.ctloop = ctloop;
        }

        public CtLoop data() {
            return this.ctloop;
        }

        public SpoonBlock getBlock() {
            return new SpoonBlock((CtBlock<?>) this.ctloop.getBody());
        }

    };

    /**
     * An abstraction for a Spoon CtSynchronized.
     */
    public static class SpoonSync implements SpoonObj {
        private CtSynchronized ctsync;

        public SpoonSync(CtSynchronized ctsync) {
            this.ctsync = ctsync;
        }

        public CtSynchronized data() {
            return this.ctsync;
        }

        public SpoonBlock getBlock() {
            return new SpoonBlock(this.ctsync.getBlock());
        }

    };

    /**
     * An abstraction for a Spoon CtTry.
     */
    public static class SpoonTryCatch implements SpoonObj {
        private CtTry cttry;

        public SpoonTryCatch(CtTry cttry) {
            this.cttry = cttry;
        }

        public CtTry data() {
            return this.cttry;
        }

        public SpoonBlock getBlock() {
            return new SpoonBlock(this.cttry.getBody());
        }

    };

    /**
     * An abstraction for a Spoon CtIf.
     */
    public static class SpoonIfElse implements SpoonObj {
        private CtIf ctif;
        private CtBlock<?> ctelse;

        public SpoonIfElse(CtIf ctif) {
            this.ctif = ctif;
            this.ctelse = null;
        }

        public SpoonIfElse(CtBlock<?> ctelse) {
            this.ctif = null;
            this.ctelse = ctelse;
        }

        public SpoonBlock getBlock() {
            if (this.ctelse != null) {
                return new SpoonBlock(this.ctelse);
            }
            return new SpoonBlock((CtBlock<?>) this.ctif.getThenStatement());
        }

    };

    /**
     * An abstraction for a Spoon CtCase.
     */
    public static class SpoonCase implements SpoonObj {
        private CtCase<?> ctcase;

        public SpoonCase(CtCase<?> ctcase) {
            this.ctcase = ctcase;
        }

        public CtCase<?> data() {
            return this.ctcase;
        }

        public SpoonBlock getBlock() {
            return new SpoonBlock((CtBlock<?>) this.ctcase.getStatements().get(0));
        }

    };

    /**
     * An abstraction for a Spoon CtBlock.
     */
    public static class SpoonBlock implements SpoonObj {
        private CtBlock<?> ctblock;

        public SpoonBlock(CtBlock<?> ctblock) {
            this.ctblock = ctblock;
        }

        public CtBlock<?> data() {
            return this.ctblock;
        }

        /**
         * Get the number of statements in the block,
         * @return number of statements
         */
        public int getStmtCount() {
            List<CtStatement> stmts = new ArrayList<>(this.ctblock.getStatements());
            int start_idx = stmts.size();
            int ret = 0;
            for (int idx = stmts.size() - 1; idx >= 0; idx--) {
                if ((stmts.get(idx) instanceof CtLocalVariable) ||
                    (stmts.get(idx).toString().contains("FuzzerUtils.init"))
                ) {
                    break;
                }
                start_idx = idx;
            }
            for (int idx = start_idx; idx < stmts.size(); idx++) {
                if ((stmts.get(idx) instanceof CtReturn<?>)
                    || (stmts.get(idx) instanceof CtThrow)
                    || (stmts.get(idx) instanceof CtBreak)
                    || (stmts.get(idx) instanceof CtContinue)
                ) {
                    break;
                }
                ret += 1;
            }
            return ret;
        }

        /**
         * Add a statement at the specified index in the block,
         * @param index index to insert at
         * @param statement statement to insert
         */
        public void addStatement(int index, CtStatement statement) {
            List<CtStatement> stmts = new ArrayList<>(this.ctblock.getStatements());
            if (stmts.isEmpty() || stmts.size() == 0) {
                Logger.debug("Before insertion at idx 0: \n"
                    + this.ctblock.toString());
                this.ctblock.addStatement(statement);
                return;
            }
            int start_idx = stmts.size();
            int end_idx = stmts.size();
            for (int idx = stmts.size() - 1; idx >= 0; idx--) {
                if ((stmts.get(idx) instanceof CtLocalVariable) ||
                    (stmts.get(idx).toString().contains("FuzzerUtils.init"))
                ) {
                    break;
                }
                start_idx = idx;
            }
            for (int idx = start_idx; idx < stmts.size(); idx++) {
                if ((stmts.get(idx) instanceof CtReturn<?>)
                    || (stmts.get(idx) instanceof CtThrow)
                    || (stmts.get(idx) instanceof CtBreak)
                    || (stmts.get(idx) instanceof CtContinue)
                ) {
                    break;
                }
                end_idx = idx;
            }
            Logger.debug("Before insertion at idx " + (Math.min(start_idx + index, end_idx)) + ": \n"
                + this.ctblock.toString());
            int final_idx = Math.max(Math.min(start_idx + index, end_idx), 0);
            this.ctblock.addStatement(final_idx, statement);
        }

        /**
         * Extract and remove up to 5 statements from the block,
         * @return extracted statements as a string
         */
        public List<String> extractAndRemoveStmts(int maxStmtNum) {
            List<String> ret = new ArrayList<>();
            List<CtStatement> stmts = new ArrayList<>(this.ctblock.getStatements());
            if (stmts.isEmpty()) {
                ret.add(new String("if (false) { System.out.println(\"Ciallo～(∠·ω< )⌒★\"); }\n"));
                return ret;
            }

            List<CtStatement> picked = new ArrayList<>(maxStmtNum);
            int start_idx = stmts.size();
            for (int idx = stmts.size() - 1; idx >= 0; idx--) {
                if ((stmts.get(idx) instanceof CtLocalVariable) ||
                    (stmts.get(idx).toString().contains("FuzzerUtils.init"))
                ) {
                    break;
                }
                start_idx = idx;
            }
            for (int idx = start_idx; idx < stmts.size(); idx++) {
                if (picked.size() == maxStmtNum) break;
                if ((stmts.get(idx) instanceof CtReturn<?>)
                    || (stmts.get(idx) instanceof CtThrow)
                    || (stmts.get(idx) instanceof CtBreak)
                    || (stmts.get(idx) instanceof CtContinue)
                ) {
                    break;
                }
                picked.add(stmts.get(idx));
            }
            if (picked.isEmpty()) {
                ret.add(new String("if (false) { System.out.println(\"Ciallo～(∠·ω< )⌒★\"); }\n"));
                return ret;
            }

            for (int i = 0; i < picked.size(); i++) {
                CtStatement st = picked.get(i);
                String code = st.toString().trim();
                if (code != null && !code.isEmpty()) {
                    if (!code.endsWith(";")) {
                        code = code + ";";
                    }
                    if (!code.endsWith("\n")) {
                        code = code + "\n";
                    }
                    ret.add(new String(code));
                }
            }

            for (CtStatement st : picked) {
                this.ctblock.removeStatement(st);
            }

            return ret;
        }

    };

}
