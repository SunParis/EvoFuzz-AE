package com.allfuzzer.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import jdk.incubator.vector.*;

import com.allfuzzer.util.Logger;

/**
 * Config: Configuration model for AllFuzzer.
 */
public class Config {
    
    private static int arraySize = 0;
    private static int stmtCnt;
    private static String srcPath = null;
    private static String outPath = null;
    private static List<String> codeBricks = new ArrayList<>();
    private static List<Integer> codeBricksPriority = new ArrayList<>();
    private static String dataOutput = null;
    private static boolean insertInOnePoint = false;
    private static boolean insertInOrder = false;
    private static boolean addInsertFlag = false;
    private static boolean clearInsertFlag = false;
    private static boolean insertInPrevPoint = false;

    private static Set<String> loadIgnoreList(String ignorelistpath) {
        Set<String> ignoreList = new HashSet<>();
        if (ignorelistpath == null || ignorelistpath.isEmpty()) {
            return ignoreList;
        }
        Path ignoreListPath = Paths.get(ignorelistpath);
        if (!Files.exists(ignoreListPath) || !Files.isRegularFile(ignoreListPath)) {
            Logger.error("loadIgnoreList: ignore list file does not exist: " + ignoreListPath);
            System.exit(1);
        }
        try {
            List<String> readlines = Files.readAllLines(ignoreListPath);
            for (String line : readlines) {
                String trimmed = line.trim();
                if (!trimmed.isEmpty()) {
                    ignoreList.add(trimmed);
                }
            }
        } catch (IOException e) {
            Logger.error("loadIgnoreList: I/O error while reading ignore list.");
            Logger.error(e);
            System.exit(1);
        }
        return ignoreList;
    }

    private static void loadCodeBricks(Path codeBricksDir, Set<String> ignoreList) {
        if (codeBricksDir == null || !Files.exists(codeBricksDir)) {
            Logger.error("readCodeBricks: directory does not exist: " + codeBricksDir);
            System.exit(1);
        }

        try {
            if (Files.isDirectory(codeBricksDir)) {
                Stream<Path> stream = Files.walk(codeBricksDir);
                stream.filter(Files::isRegularFile)
                    .filter(p -> p.toString().endsWith(".java"))
                    .forEach(p -> {
                        Logger.info("Found Java file: " + p.toAbsolutePath().toString());
                        if (ignoreList.contains(p.getFileName().toString())) {
                            Logger.info("Ignoring mutator file: " + p.toAbsolutePath().toString());
                            return;
                        }
                        codeBricks.add(p.toAbsolutePath().toString());
                        codeBricksPriority.add(-1); // Default priority
                    });
                stream.close();
            }
            else {
                List<String> readlines = Files.readAllLines(codeBricksDir);
                for (String line : readlines) {
                    String trimmed = line.trim();
                    int priority = -1;
                    if (!trimmed.isEmpty()) {
                        if (trimmed.indexOf(',') != -1) {
                            trimmed = trimmed.substring(0, trimmed.indexOf(',')).trim();
                            priority = Integer.parseInt(
                                line.substring(line.indexOf(',') + 1).trim());
                        }
                        if (!Files.exists(Paths.get(trimmed))) {
                            Logger.warning("readCodeBricks: code brick file does not exist: " + trimmed);
                            continue;
                        }
                        Logger.info("Found Java file: " + trimmed);
                        if (ignoreList.contains(trimmed) || !trimmed.endsWith(".java")) {
                            Logger.info("Ignoring mutator file: " + trimmed);
                            continue;
                        }
                        codeBricks.add(trimmed);
                        codeBricksPriority.add(priority);
                    }
                }
            }
        } catch (Exception e) {
            Logger.error("readCodeBricks: I/O error while traversing.");
            Logger.error(e);
            System.exit(1);
        }
    }

    public static final String GbClsName = "AllFuzzerDefs_" + 
        System.currentTimeMillis() + "_" + (System.nanoTime() % 100000L);

    /**
     * Get the configured maximum array size.
     * @return The maximum array size.
     */
    public static int getArraySize() {
        return arraySize;
    }

    /**
     * Get the configured number of statements to insert.
     * @return The number of statements.
     */
    public static int getStmtCnt() {
        return stmtCnt;
    }

    /**
     * Get the configured source file path.
     * @return The source file path.
     */
    public static String getSrcPath() {
        return srcPath;
    }

    /**
     * Get the configured output file path.
     * @return The output file path.
     */
    public static String getOutPath() {
        return outPath;
    }

    /**
     * Get the data output path.
     * @return The data output path.
     */
    public static String getDataOutput() {
        return dataOutput;
    }

    /**
     * Get the set of code brick file paths.
     * @return The set of code brick file paths.
     */
    public static List<String> getCodeBricks() {
        return codeBricks;
    }

    /**
     * Get the priority list of code bricks.
     * @return The priority list of code bricks.
     */
    public static List<Integer> getCodeBricksPriority() {
        return codeBricksPriority;
    }

    /**
     * Check if insertion is to be done at one point.
     * @return True if insertion is to be done at one point, false otherwise.
     */
    public static boolean isInsertInOnePoint() {
        return insertInOnePoint;
    }

    /**
     * Check if insertion is to be done in order.
     * @return True if insertion is to be done in order, false otherwise.
     */
    public static boolean isInsertInOrder() {
        return insertInOrder;
    }

    /**
     * Check if insert flag should be added.
     * @return True if insert flag should be added, false otherwise.
     */
    public static boolean isAddInsertFlag() {
        return addInsertFlag;
    }

    /**
     * Check if insert flags should be cleared before insertion.
     * @return True if insert flags should be cleared before insertion, false otherwise.
     */
    public static boolean isClearInsertFlag() {
        return clearInsertFlag;
    }

    /**
     * Check if insertion is to be done in previous point.
     * @return True if insertion is to be done in previous point, false otherwise.
     */
    public static boolean isInsertInPrevPoint() {
        return insertInPrevPoint;
    }

    private Config() {
        // Private constructor to prevent instantiation
    }

    /**
     * Load configuration from command-line arguments.
     * @param args Command-line arguments.
     */
    public static void loadConfig(String[] args) {
        
        if (args == null) {
            Logger.error("\n" + Config.helpMessage);
            throw new IllegalArgumentException("No arguments provided. Use -h for help.");
        }
        if (arraySize != 0) {
            throw new IllegalStateException("Configuration has already been loaded.");
        }
            
        if (args.length == 1 && args[0].equals("-h")) {
            System.out.println(Config.helpMessage);
            System.exit(0);
        }

        String ignoreListPath = null;
        String codeBricksDir = null;
        
        for (int i = 0; i < args.length; i += 2) {
            if (args[i].equals("--input-file")) {
                Config.srcPath = args[i + 1];
            }
            else if (args[i].equals("--output-path")) {
                Config.outPath = args[i + 1];
            }
            else if (args[i].equals("--stmt-cnt")) {
                Config.stmtCnt = Integer.valueOf(args[i + 1]);
            }
            else if (args[i].equals("--log-level")) {
                Logger.setLogLevel(args[i + 1].equals("DEBUG") ? Logger.LogLevel.DEBUG : Logger.LogLevel.INFO);
            }
            else if (args[i].equals("--arr-size")) {
                Config.arraySize = Integer.valueOf(args[i + 1]);
            }
            else if (args[i].equals("--code-brick-path")) {
                codeBricksDir = args[i + 1];
            }
            else if (args[i].equals("--ignore-list-path")) {
                ignoreListPath = args[i + 1];
            }
            else if (args[i].equals("--data-output-path")) {
                Config.dataOutput = args[i + 1];
            }
            else if (args[i].equals("--insert-in-one-point")) {
                Config.insertInOnePoint = Boolean.parseBoolean(args[i + 1]);
            }
            else if (args[i].equals("--insert-in-order")) {
                Config.insertInOrder = Boolean.parseBoolean(args[i + 1]);
            }
            else if (args[i].equals("--add-insert-flag")) {
                Config.addInsertFlag = Boolean.parseBoolean(args[i + 1]);
            }
            else if (args[i].equals("--clear-insert-flags")) {
                Config.clearInsertFlag = Boolean.parseBoolean(args[i + 1]);
            }
            else if (args[i].equals("--insert-in-prevpoint")) {
                Config.insertInPrevPoint = Boolean.parseBoolean(args[i + 1]);
            }
            else {
                Logger.error("\n" + Config.helpMessage);
                throw new IllegalArgumentException("Unknown argument: " + args[i]);
            }
        }

        if (Config.getSrcPath() == null || Config.getOutPath() == null) {
            throw new IllegalArgumentException("Input and output file paths must be specified.");
        }
        if (Files.notExists(Paths.get(Config.getSrcPath()))) {
            throw new IllegalArgumentException("Input source file does not exist: " + Config.getSrcPath());
        }
        if (!Config.getSrcPath().endsWith(".java")) {
            throw new IllegalArgumentException("Input source file must be a Java file: " + Config.getSrcPath());
        }
        if (Config.getSrcPath().indexOf("/") != -1) {
            Config.outPath = Config.getOutPath() + 
                Config.getSrcPath().substring(Config.getSrcPath().lastIndexOf("/"));
        }
        else {
            Config.outPath = Config.getOutPath() + "/" + Config.getSrcPath();
        }

        if (codeBricksDir == null) {
            throw new IllegalArgumentException("Either code bricks directory or list must be specified.");
        }
        if (Config.getStmtCnt() <= 0) {
            throw new IllegalArgumentException("Statement count must be positive.");
        }

        int min_array_size = IntVector.SPECIES_PREFERRED.length();
        if (Config.getArraySize() < min_array_size + 10) {
            Config.arraySize = min_array_size + 10;
            Logger.warning("Array size too small. Adjusted to " + Config.getArraySize());
        }

        Config.loadCodeBricks(Paths.get(codeBricksDir), Config.loadIgnoreList(ignoreListPath));
        if (Config.codeBricks.isEmpty()) {
            throw new IllegalArgumentException("No valid code bricks found.");
        }

        Logger.info("Configuration loaded successfully.");
    
    }

    final static String helpMessage = "Usage: java -jar target/AllFuzzer-1.0-SNAPSHOT.jar\n"
            + "    --input-file <input-file-path>          Path to the input Java source file.\n"
            + "    --output-path <output-file-path>        Path to the output Java source file.\n"
            + "    --stmt-cnt <number-of-statements>       Number of statements to insert.\n"
            + "    --log-level <log-level>                 Logging level (DEBUG or INFO, default: INFO).\n"
            + "    --arr-size <array-size>                 Maximum size for generated arrays.\n"
            + "    --code-brick-path <code-bricks-path>    Path to the code bricks dir or a file contains list of code brick file paths.\n"
            + "    --ignore-list-path <ignore-list-path>   Path to the ignore list file.\n"
            + "    --data-output-path <data-output-path>   Path to the data output file.\n"
            + "    --insert-in-one-point <true|false>      Whether to insert all statements at one point (default: false).\n"
            + "    --insert-in-order <true|false>          Whether to insert statements in order (default: false).\n"
            + "    -h                                      Display this help message.\n";

}

