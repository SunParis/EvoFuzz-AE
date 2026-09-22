package com.allfuzzer.spoon;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Collectors;

import com.allfuzzer.config.Config;
import com.allfuzzer.util.Logger;
import com.allfuzzer.spoon.SpoonCode.*;

import spoon.Launcher;
import spoon.reflect.code.*;
import spoon.reflect.declaration.*;
import spoon.reflect.visitor.filter.TypeFilter;

/**
 * SpoonFacade: Provide simplified access to Spoon functionalities.
 */
public class SpoonFacade {

    private Launcher launcher = null;
    private SpoonMethod mainMethod = null;
    
    private List<SpoonCls> classes = null;
    private List<SpoonInterFace> interfaces = null;
    private Map<String, List<SpoonMethod>> cls2Method = null; // class name -> methods
    private Set<String> existingImports = null; // existing import statements
    public String sourcePath = null;

    public SpoonFacade(String inputResource, boolean addBlocks, boolean autoImport) {
        this.sourcePath = inputResource;
        this.launcher = new Launcher();
        this.launcher.addInputResource(inputResource);
        this.launcher.getEnvironment().setNoClasspath(true);
        this.launcher.getEnvironment().setAutoImports(autoImport);
        this.launcher.getEnvironment().setComplianceLevel(21);
        this.launcher.getEnvironment().setCommentEnabled(true);
        this.launcher.buildModel();

        this.cls2Method = new HashMap<>();
        if (addBlocks) {
            boolean modified = true;
            while (modified) {
                modified = false;
                List<CtClass<?>> clss = this.launcher.getModel()
                    .getElements(new TypeFilter<CtClass<?>>(CtClass.class))
                    .stream()
                    .filter(sc -> sc.isTopLevel())
                    .filter(sc -> !sc.getSimpleName().contains("ExceptionTest"))
                    .filter(sc -> !sc.getSimpleName().startsWith("AllFuzzerDefs"))
                    .collect(Collectors.toList());
                for (CtClass<?> cls : clss) {
                    List<CtMethod<?>> methods = cls.getMethods().stream()
                        .filter(method -> method.getDeclaringType().equals(cls))
                        .collect(Collectors.toList());
                    for (CtMethod<?> method : methods) {
                        if (SpoonFacade.addLoopBlocks(method, launcher)) {
                            modified = true;
                            break;
                        }
                        if (SpoonFacade.addIfBlocks(method, launcher)) {
                            modified = true;
                            break;
                        }
                        if (SpoonFacade.addTryBlocks(method, launcher)) {
                            modified = true;
                            break;
                        }
                        if (SpoonFacade.addCaseBlocks(method, launcher)) {
                            modified = true;
                            break;
                        }
                    }
                    if (modified) {
                        break;
                    }
                }
            }
        }
    }

    private static boolean addLoopBlocks(CtMethod<?> method, Launcher launcher) {
        List<CtLoop> alllp = method.getElements(new TypeFilter<>(CtLoop.class));
        for (CtLoop loop : alllp) {
            if (loop.getBody() == null || loop.getBody() instanceof CtBlock == false) {
                CtBlock<?> body = launcher.getFactory().Core().createBlock();
                if (loop.getBody() != null) {
                    body.addStatement(loop.getBody().clone());
                }
                loop.setBody(body);
                return true;
            }
        }
        return false;
    }

    private static boolean addIfBlocks(CtMethod<?> method, Launcher launcher) {
        List<CtIf> allIfs = method.getElements(new TypeFilter<>(CtIf.class));
        for (CtIf root: allIfs) {
            if (root.getParent() instanceof CtIf && ((CtIf) root.getParent()).getElseStatement() == root) {
                continue;
            }
            CtIf cursor = root;
            while (cursor != null) {
                CtStatement thenStmt = cursor.getThenStatement();
                if (!(thenStmt instanceof CtBlock)) {
                    CtBlock<?> block = launcher.getFactory().Core().createBlock();
                    if (thenStmt != null) block.addStatement(thenStmt.clone());
                    cursor.setThenStatement(block);
                    return true;
                }
                
                CtStatement nxtStmt = cursor.getElseStatement();
                cursor = (nxtStmt instanceof CtIf) ? (CtIf) nxtStmt : null;
            }
        }
        return false;
    }

    private static boolean addTryBlocks(CtMethod<?> method, Launcher launcher) {
        List<CtTry> allTries = method.getElements(new TypeFilter<>(CtTry.class));
        for (CtTry trycatch : allTries) {
            if (trycatch.getBody() == null || trycatch.getBody() instanceof CtBlock == false) {
                CtBlock<?> body = launcher.getFactory().Core().createBlock();
                if (trycatch.getBody() != null) {
                    body.addStatement(trycatch.getBody().clone());
                }
                trycatch.setBody(body);
                return true;
            }
        }
        return false;
    }

    private static boolean addCaseBlocks(CtMethod<?> method, Launcher launcher) {
        List<CtSwitch<?>> allSw = method.getElements(new TypeFilter<CtSwitch<?>>(CtSwitch.class));
        for (CtSwitch<?> ctswitch : allSw) {
            List<? extends CtCase<?>> cases = ctswitch.getCases();
            boolean modified = false;
            for (CtCase<?> ctcase : cases) {
                if (ctcase.getStatements() == null || ctcase.getStatements().isEmpty()) {
                    continue;
                }
                if (ctcase.getStatements().get(0) instanceof CtBlock == false) {
                    CtBlock<?> body = launcher.getFactory().Core().createBlock();
                    if (ctcase.getStatements() != null) {
                        // clone each statement before adding to avoid parent-link check errors
                        for (CtStatement stmt : ctcase.getStatements()) {
                            body.addStatement(stmt.clone());
                        }
                    }
                    ctcase.setStatements(List.of(body));
                    modified = true;
                }
            }
            if (modified) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get all classes in the model.
     * @return List of SpoonCls representing all classes.
     */
    public List<SpoonCls> getAllClasses() {
        if (this.classes != null) return this.classes;
        this.classes = this.launcher.getModel()
            .getElements(new TypeFilter<>(CtClass.class))
            .stream()
            .filter(sc -> sc.isTopLevel())
            .filter(sc -> !sc.getSimpleName().contains("ExceptionTest"))
            .filter(sc -> !sc.getSimpleName().startsWith("AllFuzzerDefs"))
            .map(cls -> {
                return new SpoonCls((CtClass<?>) cls);
            })
            .collect(Collectors.toList());
        return this.classes;
    }

    /**
     * Get all interfaces in the model.
     * @return List of SpoonInterFace representing all interfaces.
     */
    public List<SpoonInterFace> getAllInterfaces() {
        if (this.interfaces != null) return this.interfaces;
        this.interfaces = this.launcher.getModel()
            .getElements(new TypeFilter<>(CtInterface.class))
            .stream()
            .filter(sc -> sc.isTopLevel())
            .map(interf -> {
                return new SpoonInterFace((CtInterface<?>) interf);
            })
            .collect(Collectors.toList());
        return this.interfaces;
    }

    /**
     * Get all functions in the model.
     * @return List of SpoonMethod representing all functions.
     */
    public List<SpoonMethod> getAllFuncs() {
        List<SpoonMethod> ret = new ArrayList<>();
        for (SpoonCls cls : this.getAllClasses()) {
            ret.addAll(this.getAllFuncs(cls, true));
        }
        this.cls2Method = new HashMap<>();
        return ret;
    }

    /**
     * Get all functions in a class.
     * @param cls The SpoonCls object representing the class.
     * @return List of SpoonMethod representing all functions in the class.
     */
    public List<SpoonMethod> getAllFuncs(SpoonCls cls, boolean includeMain) {
        if (this.cls2Method.containsKey(cls.getSimpleName())) {
            return this.cls2Method.get(cls.getSimpleName());
        }

        List<SpoonMethod> ret = null;
        if (!includeMain) {
            ret = cls.data().getMethods().stream()
                .filter(method -> method.getDeclaringType().equals(cls.data()))
                .filter(method -> method.getBody() != null)
                .filter(method -> !"main".equals(method.getSimpleName()))
                .map(method -> {
                    return new SpoonMethod(method);
                })
                .collect(Collectors.toList());
        }
        else {
            ret = cls.data().getMethods().stream()
                .filter(method -> method.getDeclaringType().equals(cls.data()))
                .filter(method -> method.getBody() != null)
                .map(method -> {
                    return new SpoonMethod(method);
                })
                .collect(Collectors.toList());
        }
        return ret;
    }

    /**
     * Get all loops in a function.
     * @param method The SpoonMethod object representing the function.
     * @return List of SpoonLoop representing all loops in the function.
     */
    public List<SpoonLoop> getAllLoops(SpoonMethod method) {
        List<SpoonLoop> ret = method.data()
            .getElements(new TypeFilter<>(CtLoop.class))
            .stream()
            .filter(loop -> loop.getBody() != null && loop.getBody() instanceof CtBlock)
            .map(loop -> {
                return new SpoonLoop((CtLoop) loop);
            })
            .collect(Collectors.toList());
        return ret;
    }


    /**
     * Get all synchronized blocks in a function.
     * @param method The SpoonMethod object representing the function.
     * @return List of SpoonSync representing all synchronized blocks in the function.
     */
    public List<SpoonSync> getAllSyncs(SpoonMethod method) {
        List<SpoonSync> ret = method.data()
            .getElements(new TypeFilter<>(CtSynchronized.class))
            .stream()
            .filter(sync -> sync.getBlock() != null && sync.getBlock() instanceof CtBlock)
            .map(sync -> {
                return new SpoonSync((CtSynchronized) sync);
            })
            .collect(Collectors.toList());
        return ret;
    }    

    /**
     * Get all try-catch blocks in a function.
     * @param method The SpoonMethod object representing the function.
     * @return List of SpoonTryCatch representing all try-catch blocks in the function.
     */
    public List<SpoonTryCatch> getAllTryCatches(SpoonMethod method) {

        List<SpoonTryCatch> ret = method.data()
            .getElements(new TypeFilter<>(CtTry.class))
            .stream()
            .filter(trycatch -> trycatch.getBody() != null && trycatch.getBody() instanceof CtBlock)
            .map(trycatch -> {
                return new SpoonTryCatch((CtTry) trycatch);
            })
            .collect(Collectors.toList());
        return ret;
    }

    /**
     * Get all if statements in a function.
     * @param method The SpoonMethod object representing the function.
     * @return List of SpoonIf representing all if statements in the function.
     */
    public List<SpoonIfElse> getAllIfs(SpoonMethod method) {
        List<CtIf> allIfs =  method.data().getElements(new TypeFilter<>(CtIf.class));
        List<SpoonIfElse> result = new ArrayList<>();
        for (CtIf root : allIfs) {
            if (root.getParent() instanceof CtIf && ((CtIf) root.getParent()).getElseStatement() == root) {
                continue;
            }
            CtIf cursor = root;
            while (cursor != null) {
                CtStatement thenStmt = cursor.getThenStatement();
                if (thenStmt instanceof CtBlock) {
                    result.add(new SpoonIfElse(cursor));
                }
                CtStatement elseStmt = cursor.getElseStatement();
                if (elseStmt != null && (elseStmt instanceof CtBlock)) {
                    result.add(new SpoonIfElse((CtBlock<?>) elseStmt));
                }
                CtStatement nxtStmt = cursor.getElseStatement();
                cursor = (nxtStmt instanceof CtIf) ? (CtIf) nxtStmt : null;
            }
        }
        return result;
    }

    /**
     * Get all switch statements in a function.
     * @param method The SpoonMethod object representing the function.
     * @return List of SpoonSwitch representing all switch statements in the function.
     */
    public List<SpoonCase> getAllCases(SpoonMethod method) {
        List<CtSwitch<?>> allSw = method.data().getElements(new TypeFilter<CtSwitch<?>>(CtSwitch.class));
        List<SpoonCase> ret = new ArrayList<>();
        for (CtSwitch<?> ctswitch : allSw) {
            List<? extends CtCase<?>> cases = ctswitch.getCases();
            for (CtCase<?> ctcase : cases) {
                if (ctcase.getStatements() == null || ctcase.getStatements().isEmpty()) {
                    continue;
                }
                SpoonCase spoonCase = new SpoonCase(ctcase);
                ret.add(spoonCase);
            }
        }
        return ret;
    }

    /**
     * Get the main method of the program.
     * @return SpoonMethod representing the main method, or null if not found.
     */
    public SpoonMethod getMainMethod() {
        if (this.mainMethod != null) return this.mainMethod;        
        for (SpoonCls cls : getAllClasses()) {
            for (CtMethod<?> method : cls.data().getMethods()) {
                if ("main".equals(method.getSimpleName())) {
                    this.mainMethod = new SpoonMethod(method);
                }
            }
        }
        return this.mainMethod;
    }

    /**
     * Get existing import statements in the source code.
     * @return Set of import statements as strings.
     */
    public Set<String> getImports() {
        if (this.existingImports != null) return this.existingImports;
        if (this.launcher.getFactory().CompilationUnit().getMap().isEmpty()) {
            this.existingImports = new HashSet<>();
            return this.existingImports;
        }
        this.existingImports = new HashSet<>(this.launcher.getFactory()
            .CompilationUnit().getMap().values()
            .iterator().next().getImports().stream()
            .map(CtImport::toString).collect(Collectors.toSet()));
        return this.existingImports;
    }

    /**
     * Insert statements into a SpoonObj (method, loop, etc.)
     * @param spoonObj The SpoonObj to insert statements into.
     * @param stmts List of statements to insert.
     */
    public void insertStmts(SpoonObj spoonObj, List<String> stmts, boolean randomPos) {
        if (stmts == null || stmts.size() == 0) return;
        
        if (spoonObj instanceof SpoonBlock) {
            SpoonInserter.insertBlockBody(this.launcher, (SpoonBlock) spoonObj, stmts, randomPos);
        }
        // else if (spoonObj instanceof SpoonMethod) {
        //     SpoonInserter.insertFuncBody(this.launcher, (SpoonMethod) spoonObj, stmts, randomPos);
        // }
        // else if (spoonObj instanceof SpoonLoop) {
        //     SpoonInserter.insertLoopBody(this.launcher, (SpoonLoop) spoonObj, stmts, randomPos);
        // }
        // else if (spoonObj instanceof SpoonSync) {
        //     SpoonInserter.insertSyncBody(this.launcher, (SpoonSync) spoonObj, stmts, randomPos);
        // }
        // else if (spoonObj instanceof SpoonTryCatch) {
        //     SpoonInserter.insertTryBody(this.launcher, (SpoonTryCatch) spoonObj, stmts, randomPos);
        // }
        // else if (spoonObj instanceof SpoonIf) {
        //     SpoonInserter.insertIfBody(this.launcher, (SpoonIf) spoonObj, stmts, randomPos);
        // }
        // else if (spoonObj instanceof SpoonCase) {
        //     SpoonInserter.insertCaseBody(this.launcher, (SpoonCase) spoonObj, stmts, randomPos);
        // }
        else {
            // Any other types are not supported for statement insertion
            Logger.error("Unsupported SpoonObject type for inserting statements.");
        }
    }

    /**
     * Insert local variables into a method, along with their initialization and check statements.
     * @param method The SpoonMethod object
     * @param localVars map of variable types to variable names
     * @param newMems map of variable names to new memory allocation expressions
     * @param iniExprs map of variable names to initialization expressions
     * @param checkStmts map of variable names to check/print statements
     */
    public void insertLocalVars(SpoonMethod method, Map<String, Set<String>> localVars,
        Map<String, String> newMems, Map<String, String> iniExprs, Map<String, String> checkStmts
    ) {
        try {
            SpoonInserter.insertLocalVars(this.launcher, method, localVars, newMems, iniExprs, checkStmts);
        } catch (Exception e) {
            Logger.error("Failed to insert local variables into method: " + method.getUniqueName());
            Logger.error(e);
        }
    }

    /**
     * Insert global variables into a class, along with their initialization and check statements.
     * @param launcher spoon launcher
     * @param clazz class object
     * @param gbVars map of variable types to variable names
     * @param newMems map of variable names to new memory allocation expressions
     * @param iniExprs map of variable names to initialization expressions
     * @param checkStmts map of variable names to check/print statements
     */
    public void insertGBVars(SpoonCls clazz, Map<String, Set<String>> gbVars, 
        Map<String, String> newMems, Map<String, String> iniExprs, Map<String, String> checkStmts
    ) {
        try {
            SpoonInserter.insertGBVars(this.launcher, clazz, gbVars, newMems, iniExprs, checkStmts);
        } catch (Exception e) {
            Logger.error("Failed to insert global variables into class: " + clazz.getSimpleName());
            Logger.error(e);
        }
    }

    /**
     * Write back the modified AST to Java source files.
     * @param outputPath The output directory path.
     * @param imports List of import statements to add.
     * @param funcs List of function definitions to add.
     * @param clazzs List of class definitions to add.
     * @param comments Set of comments to add.
     * TODO: We had better add funcs and imports in a "spoon" way rather than string concatenation.
     */
    public void writeBackAST(String outputPath, Set<String> imports, List<String> funcs, List<String> clazzs, Set<String> comments) {
        String fakeMainName = "AllFuzzer_FakeMain_" + System.currentTimeMillis() + "_" + (int)(Math.random() * 10000);
        String trueMain = "";
        trueMain += "    public static void main(java.lang.String[] args) {\n";
        trueMain += "        try {\n";
        trueMain += "            " + fakeMainName + "(args);\n";
        trueMain += "        } catch (java.lang.OutOfMemoryError ex) {\n";
        trueMain += "            ex.printStackTrace(System.err);\n";
        trueMain += "            System.exit(1);\n";
        trueMain += "        } catch (java.lang.Exception ex) {\n";
        trueMain += "            System.out.println(ex.getClass().getCanonicalName());\n";
        trueMain += "        } catch (java.lang.Throwable ex) {\n";
        trueMain += "            System.out.println(ex.getClass().getCanonicalName());\n";
        trueMain += "        }\n";
        trueMain += "    }\n";
        try {            
            // Add throw statements in the try block to prevent compile errors
            for (List<SpoonMethod> methods: this.cls2Method.values()) {
                for (SpoonMethod method: methods) {
                    List<SpoonTryCatch> tries = method.data()
                        .getElements(new TypeFilter<>(CtTry.class))
                        .stream()
                        .filter(try_catch -> try_catch.getParent(CtMethod.class).equals(method.data()))
                        .map(trycatch -> {
                            return new SpoonTryCatch((CtTry) trycatch);
                        })
                        .collect(Collectors.toList());
                    for (SpoonTryCatch ctTry: tries) {
                        SpoonInserter.insertThrowInTry(this.launcher, ctTry);
                    }
                }
            }            
            Path dir = Paths.get(outputPath).getParent();
            this.launcher.setSourceOutputDirectory(dir.toString());
            this.launcher.prettyprint();

            // Combine all Java files into a single Test.java file
            List<Path> javaFiles = Files.walk(dir)
                .filter(Files::isRegularFile)
                .filter(p -> p.toString().endsWith(".java"))
                .collect(Collectors.toList());

            List<String> allLines = new ArrayList<>();
            String pkgLine = null;
            
            for (Path file: javaFiles) {
                allLines.add("\n");
                List<String> fileLines = Files.readAllLines(file);
                for (String line : fileLines) {
                    if (line.trim().isEmpty())  continue;
                    if (line.startsWith("import")) continue;
                    if (line.startsWith("// compile_args: ")) continue;
                    if (line.startsWith("// run_args: ")) continue;
                    if (line.startsWith("package")) {
                        if (pkgLine == null) {
                            pkgLine = "// Auto-generated by AllFuzzer at " + 
                                java.time.LocalDateTime.now() + "\n" + line + "\n";
                        }
                        continue;
                    }
                    if (line.contains("public static void main(")) {                        
                        line = line.replace("public static void main(", "public static void " + fakeMainName + "(");
                        line = trueMain + line;
                    }
                    line = line.replace("AllFuzzerDefs.", Config.GbClsName + ".");
                    line = line.replace(";;", ";");
                    line = line.replace("    ;", "    ");
                    line = line.replace("};", "}");
                    line = line.replace("}// HelloHHHHHHHHHHHHH", "};// HelloHHHHHHHHHHHHH");
                    
                    allLines.add(line);
                }
            }

            // Add functions in a separate class
            allLines.add("\nclass " + Config.GbClsName + " {\n");
            int layer = 1;
            for (String func: funcs) {
                StringBuilder sb = new StringBuilder();
                for (String line : func.split("\n")) {
                    line = line.replace("AllFuzzerDefs.", Config.GbClsName + ".");
                    line = line.replace(";;", ";");
                    line = line.replace("    ;", "    ");
                    line = line.replace("};", "}");
                    line = line.replace("}// HelloHHHHHHHHHHHHH", "};// HelloHHHHHHHHHHHHH");
                    line = line.strip();
                    int rbrace_num = line.length() - line.replace("}", "").length();
                    int lbrace_num = line.length() - line.replace("{", "").length();
                    for (int i = 0; i < layer; i++) {
                        sb.append("    ");
                    }
                    sb.append(line).append("\n");
                    layer += (lbrace_num - rbrace_num);
                }
                allLines.add(sb.toString());
            }
            allLines.add("}\n");

            for (String clazz: clazzs) {
                clazz = clazz.replace("AllFuzzerDefs.", Config.GbClsName + ".");
                clazz = clazz.replace(";;", ";");
                clazz = clazz.replace("    ;", "    ");
                clazz = clazz.replace("};", "}");
                clazz = clazz.replace("}// HelloHHHHHHHHHHHHH", "};// HelloHHHHHHHHHHHHH");
                allLines.add(clazz);
                allLines.add("\n\n");
            }

            // Add import statements after the package declaration
            if (imports != null && imports.size() > 0) {
                allLines.addAll(0, imports);
            }
            if (pkgLine == null) {
                pkgLine = "// Auto-generated by AllFuzzer at " + 
                    java.time.LocalDateTime.now() + "\n";
            }
            for (String comment : comments) {
                pkgLine += comment + "\n";
            }
            allLines.addFirst(pkgLine);

            if (Config.isClearInsertFlag()) {
                List<String> newAllLines = new ArrayList<>();
                for (int i = 0; i < allLines.size(); i++) {
                    String line = allLines.get(i);
                    if (!line.contains("// AllFuzzer: insert Mutators Here")) {
                        newAllLines.add(line);
                    }
                }
                allLines = newAllLines;
            }

            Files.write(Paths.get(outputPath), allLines);
        } catch (Exception e) {
            Logger.error("Failed to write AST to output path: " + outputPath);
            Logger.error(e);
        }
    }

}
