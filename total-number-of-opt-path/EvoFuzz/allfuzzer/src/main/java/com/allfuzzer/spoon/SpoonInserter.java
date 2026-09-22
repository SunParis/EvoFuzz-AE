package com.allfuzzer.spoon;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

import com.allfuzzer.spoon.SpoonCode.*;

import com.allfuzzer.util.Logger;
import com.allfuzzer.util.RandomChooser;

import spoon.Launcher;
import spoon.reflect.code.*;
import spoon.reflect.declaration.*;
import spoon.reflect.reference.CtTypeReference;

public class SpoonInserter {

    private static CtCodeSnippetStatement createStmt(Launcher launcher, String stmt) {
        while (stmt.endsWith("\n")) {
            stmt = stmt.substring(0, stmt.length() - 1);
        }
        return launcher.getFactory().Code()
            .createCodeSnippetStatement(stmt);
    }

    private static SpoonBlock insertStaticBlock(Launcher launcher, SpoonCls clazz) {
        CtBlock<?> body = launcher.getFactory().Core().createBlock();
        CtAnonymousExecutable anonExe = launcher.getFactory().Core().createAnonymousExecutable();
        launcher.getFactory().Core().createAnonymousExecutable();
        anonExe.addModifier(ModifierKind.STATIC);
        anonExe.setBody(body);
        clazz.data().addAnonymousExecutable(anonExe);
        
        return new SpoonBlock(body);
    }

    public static void insertClassGB(Launcher launcher, SpoonCls clazz, String varName, String varType) {
        CtTypeReference<?> typeRef = launcher.getFactory().Type().createReference(varType);
        Set<ModifierKind> modifiers = new HashSet<>();
        modifiers.add(ModifierKind.PUBLIC);
        modifiers.add(ModifierKind.STATIC);

        CtField<?> field = launcher.getFactory().Field().create(
            clazz.data(),
            modifiers,
            typeRef,
            varName,
            null
        );
        clazz.data().addField(field);
    }

    private static SpoonMethod insertFunc(Launcher launcher, SpoonCls clazz, String funcName, String retType) {
        CtMethod<?> insertMethod = launcher.getFactory().Method().create(
            clazz.data(),
            java.util.Collections.singleton(ModifierKind.PUBLIC),
            launcher.getFactory().Type().createReference(retType),
            funcName,
            java.util.Collections.emptyList(),
            java.util.Collections.emptySet(),
            launcher.getFactory().Core().createBlock()
        );
        if (insertMethod == null) {
            throw new RuntimeException("Failed to create method: " + funcName);
        }
        clazz.data().addMethod(insertMethod);
        
        return new SpoonMethod(insertMethod);
    }

    /**
     * Insert initialization statements at the beginning of a function body.
     * @param launcher spoon launcher
     * @param method method object
     * @param localVars map of variable types to variable names
     * @param newMems map of variable names to new memory allocation expressions
     * @param iniExprs map of variable names to initialization expressions
     * @param checkStmts map of variable names to check/print statements
     */
    public static void insertLocalVars(Launcher launcher, SpoonMethod method, Map<String, Set<String>> localVars,
        Map<String, String> newMems, Map<String, String> iniExprs, Map<String, String> checkStmts
    ) {
        try {
            if (method.data().getBody() == null) {
                method.data().setBody(launcher.getFactory().Core().createBlock());
            }

            List<CtCodeSnippetStatement> newMemStmt = new ArrayList<>();
            List<CtCodeSnippetStatement> initStmts = new ArrayList<>();
            List<CtCodeSnippetStatement> printStmts = new ArrayList<>();

            for (Map.Entry<String, Set<String>> varEntry: localVars.entrySet()) {
                String varType = varEntry.getKey();
                for (String varName: varEntry.getValue()) {
                    if (newMems.containsKey(varName)) {
                        String memStmt = String.format("%s %s = %s;", varType, varName, newMems.get(varName));
                        newMemStmt.add(SpoonInserter.createStmt(launcher, memStmt));
                    }
                    else {
                        String memStmt = String.format("%s %s;", varType, varName);
                        newMemStmt.add(SpoonInserter.createStmt(launcher, memStmt));
                    }
                    
                    if (iniExprs.containsKey(varName)) {
                        String iniStmt = String.format("%s", iniExprs.get(varName));
                        initStmts.add(SpoonInserter.createStmt(launcher, iniStmt));
                    }
                    if (checkStmts.containsKey(varName)) {
                        printStmts.add(SpoonInserter.createStmt(launcher, checkStmts.get(varName)));
                    }
                }
            }

            // Insert print statements at the end of the function body
            for (CtCodeSnippetStatement stmt: printStmts) {
                if (RandomChooser.nextInt(10) < 6) {
                    Logger.debug("Adding local variable check statement: \n    " + stmt.toString());
                    method.addStmtsToEnd(stmt);
                } else {
                    Logger.debug("Skip adding local variable check statement: \n    " + stmt.toString());
                }
            }

            // Insert initialization statements at the beginning of the function body
            for (int idx = initStmts.size() - 1; idx >= 0; idx--) {
                Logger.debug("Adding local variable initialization statement: \n    " + initStmts.get(idx).toString());
                method.addStmtsToFront(initStmts.get(idx));
            }

            // Insert `new <some type>` statements at the beginning of the function body
            for (int idx = newMemStmt.size() - 1; idx >= 0; idx--) {
                Logger.debug("Adding local variable new statement: \n    " + newMemStmt.get(idx).toString());
                method.addStmtsToFront(newMemStmt.get(idx));
            }
                    
        } catch (Exception e) {
            Logger.error(e);
        }
    }

    /**
     * Insert initialization and check statements into constructors or main method.
     * @param launcher spoon launcher
     * @param clazz class object
     * @param initStmts Initialization statements
     * @param checkStmts Check/print statements
     */
    private static void insertIniCheckStmts(Launcher launcher, SpoonCls clazz,
        List<CtCodeSnippetStatement> initStmts, List<CtCodeSnippetStatement> checkStmts
    ) {
        if ((initStmts != null && initStmts.size() > 0) || (checkStmts != null && checkStmts.size() > 0)) {
            // Find main method or constructors to insert statements
            List<SpoonMethod> insertTargets = List.of(clazz.getMain());
            if (insertTargets == null) {
                insertTargets = clazz.getConstructors();
            }
            if (insertTargets == null) {
                insertTargets = List.of(SpoonInserter.insertFunc(launcher, clazz, clazz.data().getSimpleName(), "void"));
            }

            // Insert statements into the target methods
            for (SpoonMethod constructor : insertTargets) {
                CtMethod<?> ctorMethod = constructor.data();
                // Insert initialization statements at the beginning of the constructor
                for (CtCodeSnippetStatement stmt : initStmts) {
                    ctorMethod.getBody().insertBegin(stmt);
                }
                
                // Insert print statements at the end of the constructor
                for (CtCodeSnippetStatement stmt : checkStmts) {
                    ctorMethod.getBody().addStatement(stmt);
                }
            }
        }
    }

    /**
     * Insert new memory allocation statements into static block.
     * @param launcher spoon launcher
     * @param clazz class object
     * @param newMemStmt new memory allocation statements
     */
    private static void insertNewMemStmts(Launcher launcher, SpoonCls clazz, List<CtCodeSnippetStatement> newMemStmt) {
        // Insert `new <some type>` to static field
        if (newMemStmt != null && newMemStmt.size() > 0) {
            CtAnonymousExecutable existing = clazz.data().getAnonymousExecutables().stream()
                .filter(e -> e.hasModifier(ModifierKind.STATIC))
                .findFirst().orElse(null);
            CtBlock<?> body = null;
            if (existing == null) {
                body = SpoonInserter.insertStaticBlock(launcher, clazz).data();
            }
            else {
                body = existing.getBody();
            }
            for (CtCodeSnippetStatement stmt: newMemStmt) {
                body.insertBegin(stmt);
                Logger.debug("Adding static initialization statement: " + stmt.toString());
            }
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
    public static void insertGBVars(Launcher launcher, SpoonCls clazz, Map<String, Set<String>> gbVars, 
        Map<String, String> newMems, Map<String, String> iniExprs, Map<String, String> checkStmts
    ) {
        try {
            List<CtCodeSnippetStatement> newMemStmt = new ArrayList<>();
            List<CtCodeSnippetStatement> initStmts = new ArrayList<>();
            List<CtCodeSnippetStatement> printStmts = new ArrayList<>();

            // Transfer stmts (String) to CtCodeSnippetStatement
            for (Map.Entry<String, Set<String>> entry : gbVars.entrySet()) {
                String varType = entry.getKey();
                for (String varName: entry.getValue()) {
                    SpoonInserter.insertClassGB(launcher, clazz, varName, varType);
                    
                    if (newMems.containsKey(varName)) {
                        String memStmt = String.format("%s = %s;", varName, newMems.get(varName));
                        newMemStmt.add(SpoonInserter.createStmt(launcher, memStmt));
                    }
                    if (iniExprs.containsKey(varName)) {
                        String iniStmt = String.format("%s", iniExprs.get(varName));
                        initStmts.add(SpoonInserter.createStmt(launcher, iniStmt));
                    }
                    if (checkStmts.containsKey(varName)) {
                        printStmts.add(SpoonInserter.createStmt(launcher, checkStmts.get(varName)));
                    }
                }
            }

            // Insert initialization and print statements
            SpoonInserter.insertIniCheckStmts(launcher, clazz, initStmts, printStmts);
            

            // Insert `new <some type>` to static field
            SpoonInserter.insertNewMemStmts(launcher, clazz, newMemStmt);            
            
        } catch (Exception e) {
            Logger.error(e);
        }
    }

    
    /**
     * Insert statements into a block body at random positions.
     * @param launcher spoon launcher
     * @param block block object
     * @param stmts statements to insert
     * @param randomPos whether to insert at random positions
     */
    public static void insertBlockBody(Launcher launcher, SpoonBlock block, List<String> stmts, boolean randomPos) {
        try {
            for (int j = stmts.size() - 1; j >= 0; j--) {
                CtCodeSnippetStatement newStmt = SpoonInserter.createStmt(launcher, stmts.get(j));
                int position = 0;
                if (randomPos) {
                    position = RandomChooser.nextInt(block.getStmtCount());
                }
                block.addStatement(position, newStmt);
            }
        } catch (Exception e) {
            Logger.error(e);
        }
    }

    /**
     * Insert throw statements into a try block to trigger its catchers.
     * @param launcher spoon launcher
     * @param ctry try-catch block object
     */
    public static void insertThrowInTry(Launcher launcher, SpoonTryCatch ctry) {
        try {
            if (ctry.data().getCatchers() != null) {
                for (int i = 0; i < ctry.data().getCatchers().size(); i++) {
                    CtTypeReference<?> catchType = ctry.data().getCatchers().get(i).getParameter().getType();
                    CtBlock<?> tryBlock = ctry.data().getBody();
                    String targetStmt = String.format("throw new %s(\"%s\")", catchType.getQualifiedName(), RandomChooser.randomString());
                    if (ctry.data().getCatchers().size() > 0) {
                        targetStmt = String.format("if (Math.abs(%d) %% 50 == %d) { %s; }", RandomChooser.nextInt(100), i % 2, targetStmt);
                    }
                    CtCodeSnippetStatement throwStmt = SpoonInserter.createStmt(launcher, targetStmt);
                    if (tryBlock.getStatements() == null) {
                        tryBlock.addStatement(throwStmt);
                        continue;
                    }
                    
                    int insertIdx = tryBlock.getStatements().size();
                    while (true) {
                        if (insertIdx <= 0) {
                            insertIdx = 0;
                            break;
                        }
                        CtStatement lastStmt = tryBlock.getStatements().get(insertIdx - 1);
                        if (lastStmt instanceof CtThrow || 
                            lastStmt instanceof CtReturn || 
                            lastStmt instanceof CtBreak || 
                            lastStmt instanceof CtContinue
                        ) {
                            insertIdx--;
                            continue;
                        }
                        break;
                    }

                    tryBlock.addStatement(insertIdx, throwStmt);
                }
            }
        } catch (Exception e) {
            Logger.error(e);
        }
    }
    
    /**
     * Insert statements into a function body at random positions.
     * @param launcher spoon launcher
     * @param method method object
     * @param stmts statements to insert
     * @param randomPos whether to insert at random positions
     */
    // public static void insertFuncBody(Launcher launcher, SpoonMethod method, List<String> stmts, boolean randomPos) {
    //     try {
    //         if (method.data().getBody() == null) {
    //             method.data().setBody(launcher.getFactory().Core().createBlock());
    //         }
    //         for (String stmt : stmts) {
    //             CtCodeSnippetStatement bodyStmt = SpoonInserter.createStmt(launcher, stmt);
    //             int insertIdx = 0;
    //             if (randomPos) {
    //                 insertIdx = RandomChooser.nextInt(method.getBlock().getStmtCount());
    //             }
    //             method.getBlock().addStatement(insertIdx, bodyStmt);
    //         }
    //     } catch (Exception e) {
    //         Logger.error(e);
    //     }
    // }

    /**
     * Insert statements into a loop body at random positions.
     * @param launcher spoon launcher
     * @param loop loop object
     * @param stmts statements to insert
     * @param randomPos whether to insert at random positions
     */
    // public static void insertLoopBody(Launcher launcher, SpoonLoop loop, List<String> stmts, boolean randomPos) {
    //     try {
    //         CtBlock<?> loopBody = null;
    //         if (loop.data().getBody() instanceof CtBlock<?>) {
    //             loopBody = (CtBlock<?>) loop.data().getBody();
    //         } else {
    //             Logger.debug("Loop body is not a CtBlock, create one.");
    //             loopBody = launcher.getFactory().Core().createBlock();
    //             loopBody.addStatement(loop.data().getBody());
    //             loop.data().setBody(loopBody);
    //         }
    //         for (String stmt : stmts) {
    //             CtCodeSnippetStatement bodyStmt = SpoonInserter.createStmt(launcher, stmt);
    //             int insertIdx = 0;
    //             if (randomPos) {
    //                 insertIdx = RandomChooser.nextInt(loopBody.getStatements().size());
    //             }
    //             loopBody.addStatement(insertIdx, bodyStmt);
    //         }
    //     } catch (Exception e) {
    //         Logger.error(e);
    //     }
        
    // }

    /**
     * Insert statements into a synchronized block body at random positions.
     * @param launcher spoon launcher
     * @param sync synchronized block object
     * @param stmts statements to insert
     * @param randomPos whether to insert at random positions
     */
    // public static void insertSyncBody(Launcher launcher, SpoonSync sync, List<String> stmts, boolean randomPos) {
    //     try {
    //         CtBlock<?> syncBody = sync.data().getBlock();
    //         for (String stmt : stmts) {
    //             CtCodeSnippetStatement bodyStmt = SpoonInserter.createStmt(launcher, stmt);
    //             int insertIdx = 0;
    //             if (randomPos) {
    //                 insertIdx = RandomChooser.nextInt(syncBody.getStatements().size());
    //             }
    //             syncBody.addStatement(insertIdx, bodyStmt);
    //         }
    //     } catch (Exception e) {
    //         Logger.error(e);
    //     }
        
    // }

    /**
     * Insert statements into a try block body at random positions.
     * @param launcher spoon launcher
     * @param trycatch try-catch block object
     * @param stmts statements to insert
     */
    // public static void insertTryBody(Launcher launcher, SpoonTryCatch trycatch, List<String> stmts, boolean randomPos) {
    //     try {
    //         CtBlock<?> tryBody = trycatch.data().getBody();
    //         for (String stmt : stmts) {
    //             CtCodeSnippetStatement bodyStmt = SpoonInserter.createStmt(launcher, stmt);
    //             int insertIdx = 0;
    //             if (randomPos) {
    //                 insertIdx = RandomChooser.nextInt(tryBody.getStatements().size());
    //             }
    //             tryBody.addStatement(insertIdx, bodyStmt);
    //         }
    //     } catch (Exception e) {
    //         Logger.error(e);
    //     }
        
    // }

    /**
     * Insert statements into an if statement body at random positions.
     * @param launcher spoon launcher
     * @param ifstmt if statement object
     * @param stmts statements to insert
     */
    // public static void insertIfBody(Launcher launcher, SpoonIf ifstmt, List<String> stmts, boolean randomPos) {
    //     try {
    //         CtBlock<?> thenBody = ifstmt.data().getThenStatement() instanceof CtBlock<?> ?
    //             (CtBlock<?>) ifstmt.data().getThenStatement() : null;
    //         if (thenBody == null) {
    //             Logger.error("If statement's then body is not a CtBlock, cannot insert statements.");
    //             return;
    //         }
    //         if (stmts.size() >= 2 && RandomChooser.nextInt(10) < 7) {
    //             int splitIdx = RandomChooser.nextInt(stmts.size() - 1) + 1;
    //             List<String> frontStmts = stmts.subList(0, splitIdx);
    //             List<String> restStmts = stmts.subList(splitIdx, stmts.size());
    //             // Insert frontStmts at the very beginning of thenBody
    //             for (int i = frontStmts.size() - 1; i >= 0; i--) {
    //                 CtCodeSnippetStatement bodyStmt = SpoonInserter.createStmt(launcher, frontStmts.get(i));
    //                 thenBody.insertBegin(bodyStmt);
    //             }
    //             // Insert restStmts at random positions
    //             for (String stmt : restStmts) {
    //                 CtCodeSnippetStatement bodyStmt = SpoonInserter.createStmt(launcher, stmt);
    //                 int insertIdx = 0;
    //                 if (randomPos) {
    //                     insertIdx = RandomChooser.nextInt(thenBody.getStatements().size());
    //                 }
    //                 thenBody.addStatement(insertIdx, bodyStmt);
    //             }
    //         } else {
    //             for (String stmt : stmts) {
    //                 CtCodeSnippetStatement bodyStmt = SpoonInserter.createStmt(launcher, stmt);
    //                 int insertIdx = 0;
    //                 if (randomPos) {
    //                     insertIdx = RandomChooser.nextInt(thenBody.getStatements().size());
    //                 }
    //                 thenBody.addStatement(insertIdx, bodyStmt);
    //             }
    //         }
    //     } catch (Exception e) {
    //         Logger.error(e);
    //     }
        
    // }

    /**
     * Insert statements into switch case bodies at random positions.
     * @param launcher spoon launcher
     * @param cases switch cases object
     * @param stmts statements to insert
     * @param randomPos whether to insert at random positions
     */
    // public static void insertCaseBody(Launcher launcher, SpoonCase caze, List<String> stmts, boolean randomPos) {
    //     try {
    //         CtCase<?> ctcase = caze.data();
    //         CtBlock<?> caseBlock = ctcase.getStatements() instanceof CtBlock<?> block ? block : null;
    //         for (int j = stmts.size() - 1; j >= 0; j--) {
    //             CtCodeSnippetStatement newStmt = SpoonInserter.createStmt(launcher, stmts.get(j));
    //             if (caseBlock != null) {
    //                 int position = 0;
    //                 if (randomPos) {
    //                     position = RandomChooser.nextInt(caseBlock.getStatements().size());
    //                 }
    //                 caseBlock.addStatement(position, newStmt);
    //             } else {
    //                 int position = 0;
    //                 if (randomPos) {
    //                     position = RandomChooser.nextInt(ctcase.getStatements().size());
    //                 }
    //                 ctcase.addStatement(position, newStmt);
    //             }
    //         }
    //     } catch (Exception e) {
    //         Logger.error(e);
    //     }
    // }

    

}
