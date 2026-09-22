package com.allfuzzer.preprocess;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import com.allfuzzer.spoon.SpoonFacade;
import com.allfuzzer.spoon.SpoonCode.*;
import com.allfuzzer.util.RandomChooser;

import spoon.reflect.code.CtBlock;
import spoon.reflect.declaration.CtElement;

import com.allfuzzer.util.Logger;

/**
 * Analyze the host source code to find insertion points for fuzzing.
 */
public class HostAnalyzer {

    /**
     * Container for insertion points in the host code.
     */
    public static class InsertionPoints {
        
        public Map<String, Map<String, SpoonMethod>> methods;

        public Map<String, SpoonCls> clazzs;

        public Set<String> imports;

        public Set<String> args;

        private SpoonFacade spoon_;

        boolean insertInOnePoint = false;

        InsertionPoints(SpoonFacade spoon) {
            this.methods = new HashMap<>();
            this.clazzs = new HashMap<>();
            this.imports = new HashSet<>();
            this.args = new HashSet<>();
            this.spoon_ = spoon;
        }

        public void insertStatements(SpoonObj target, List<String> stmts, boolean inOnePoint) {
            boolean randomPos = !inOnePoint;
            this.spoon_.insertStmts(target, stmts, randomPos);
        }

        /**
         * Represents a potential insertion point in the host code.
         */
        public static class InsertPoint {
            public String className;
            public SpoonMethod methodobj;
            public SpoonBlock insertObj;

            public InsertPoint(String className, SpoonMethod methodobj, SpoonBlock insertObj) {
                this.className = className;
                this.methodobj = methodobj;
                this.insertObj = insertObj;
            }

            public String getUniqueMethodName() {
                return this.methodobj.getUniqueName();
            }

            public String getFullMethodPath() {
                return this.className + "." + this.methodobj.getSimpleName();
            }
        };

        private List<InsertPoint> getAllInsertPoints() {
            List<InsertPoint> spoonobj = new ArrayList<>();
            for (Map<String, SpoonMethod> methodentry : this.methods.values()) {
                for (SpoonMethod method : methodentry.values()) {
                    spoonobj.add(new InsertPoint(
                        method.getParentCls().getSimpleName(),
                        method,
                        method.getBlock()
                    ));
                    for (SpoonLoop obj : this.spoon_.getAllLoops(method)) {
                        spoonobj.add(new InsertPoint(
                            method.getParentCls().getSimpleName(),
                            method,
                            obj.getBlock()
                        ));
                    }
                    for (SpoonSync obj : this.spoon_.getAllSyncs(method)) {
                        spoonobj.add(new InsertPoint(
                            method.getParentCls().getSimpleName(),
                            method,
                            obj.getBlock()
                        ));
                    }
                    for (SpoonTryCatch obj : this.spoon_.getAllTryCatches(method)) {
                        spoonobj.add(new InsertPoint(
                            method.getParentCls().getSimpleName(),
                            method,
                            obj.getBlock()
                        ));
                    }
                    for (SpoonIfElse obj : this.spoon_.getAllIfs(method)) {
                        spoonobj.add(new InsertPoint(
                            method.getParentCls().getSimpleName(),
                            method,
                            obj.getBlock()
                        ));
                    }
                    for (SpoonCase obj : this.spoon_.getAllCases(method)) {
                        spoonobj.add(new InsertPoint(
                            method.getParentCls().getSimpleName(),
                            method,
                            obj.getBlock()
                        ));
                    }
                }
            }
            return spoonobj;
        }

        /**
         * Get a random insertion point from the analyzed host code.
         * @return A random InsertPoint object, or null if none found
         */
        public InsertPoint getRandomInsertPoint() {
            List<InsertPoint> spoonobj = this.getAllInsertPoints();
            if (spoonobj.isEmpty()) {
                return null;
            }
            if (this.insertInOnePoint) {
                InsertPoint ret = spoonobj.get(0);
                Logger.info("Pick point under: " + ret.getFullMethodPath());
                return ret;
            }
            InsertPoint ret = spoonobj.get(RandomChooser.nextInt(spoonobj.size()));
            Logger.info("Pick point under: " + ret.getFullMethodPath());
            return ret;
        }

        public InsertPoint getPrevInsertPoint() {
            List<InsertPoint> spoonobj = this.getAllInsertPoints();
            if (spoonobj.isEmpty()) {
                return null;
            }
            for (InsertPoint ip: spoonobj) {
                CtBlock<?> data = ip.insertObj.data();
                for (CtElement dd: data.getDirectChildren()) {
                    String str = dd.toString().strip();
                    if (str.startsWith("// AllFuzzer: insert Mutators Here")) {
                        return ip;
                    } else {
                        break;
                    }
                }
            }
            return null;
        }

        public InsertPoint getRandomPrevInsertPoint() {
            List<InsertPoint> spoonobj = this.getAllInsertPoints();
            if (spoonobj.isEmpty()) {
                return null;
            }
            List<InsertPoint> prevps = new ArrayList<>();
            for (InsertPoint ip: spoonobj) {
                CtBlock<?> data = ip.insertObj.data();
                for (CtElement dd: data.getDirectChildren()) {
                    String str = dd.toString().strip();
                    if (str.startsWith("// AllFuzzer: insert Mutators Here")) {
                        prevps.add(ip);
                    } else {
                        break;
                    }
                }
            }
            if (prevps.isEmpty()) {
                return null;
            }
            return prevps.get(RandomChooser.nextInt(prevps.size()));
        }

    }

    /**
     * Analyze the host source code to find insertion points.
     * @param spoon SpoonFacade instance with the host code model built
     * @param firstOnly If true, only find the first insertion point
     * @return List of insertion points (could be methods, loops, conditionals, etc.)
     */
    public static InsertionPoints analyzeHost(SpoonFacade spoon, boolean firstOnly) {
        InsertionPoints ret = new InsertionPoints(spoon);
        ret.insertInOnePoint = firstOnly;
        boolean insertMain = spoon.getAllFuncs().size() == 1;

        try {
            List<String> fileLines = Files.readAllLines(Paths.get(spoon.sourcePath));
            for (String line: fileLines) {
                if (line.startsWith("// run_args:")
                    || line.startsWith("// compile_args:")
                ) {
                    ret.args.add(line);
                }
            }
        } catch (Exception e) {
            Logger.error("Failed to read source file for run_args: " + e.getMessage());
        }      
        
        // Map: ClassName -> (MethodUniqueName -> SpoonMethod)
        Map<String, Map<String, SpoonMethod>> methods = ret.methods;
        
        for (SpoonCls clazz : spoon.getAllClasses()) {
            
            // Map: MethodUniqueName -> SpoonMethod
            Map<String, SpoonMethod> methodBody = new HashMap<>();
                        
            methods.put(clazz.getSimpleName(), methodBody);
            for (SpoonMethod method: spoon.getAllFuncs(clazz, insertMain)) {
                methodBody.put(method.getUniqueName(), method);
            }
            ret.clazzs.put(clazz.getSimpleName(), clazz);
        }
        ret.imports = spoon.getImports();
        
        return ret;
    }

}
