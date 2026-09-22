// Modified for EvoFuzz-AE: instrumentation, scheduling, execution accounting, and portable examples.
package edu.hust.xzf.mutator;

import edu.hust.xzf.mutator.config.Configuration;
import edu.hust.xzf.mutator.utils.FS;

import org.apache.commons.cli.*;

import java.io.IOException;
import java.util.List;

public class Main {

    private static final String PROJECT_PATH = "project_path"; // source code root path. e.g. /path/to/buggyProject
    private static final String TARGET_CASE = "target_case"; // target java file. e.g. a.b.c denotes a/b/c.java
    private static final String JDK = "jdk"; // the jdk directory, path to bin/
    private static final String JDK_level = "jdk_level";
    private static final String LINE_NUMBER = "line_number";

    private static final String MAX_ITER = "max_iter";

    private static final String USE_RANDOM_JIT_OPTIONS = "use_jit_options";

    private static final String USE_JTREG = "is_use_jtreg";

    private static final String ENABLE_PROFILE_GUIDE = "enable_profile_guide";

    private static final String USE_RANDOM_GC_OPTIONS = "use_gc_options";

    private static final String RCOV_JAVA_HOME = "rcov_java_home";

    private static final String ENABLE_SCHEDULER = "enable_scheduler";

    private static final String ENABLE_RCOV_RUN_ANY_WAY = "enable_rcov_run_any_way";


    protected final Options options = new Options();

    public Main() {
        initializeCommandLineOptions();
    }

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.run(args);
    }

    private void run(String[] args) {
        HelpFormatter hf = new HelpFormatter();
        hf.setWidth(110);
        CommandLine cmd = null;

        // We need proper parameters
        final HelpFormatter formatter = new HelpFormatter();
        if (args.length == 0) {
            hf.printHelp("help", options, true);
            System.exit(0);
        }
        Configuration config = null;
        try {
            CommandLineParser parser = new PosixParser();
            try {
                cmd = parser.parse(options, args);
                cmd.getArgs();
            } catch (ParseException ex) {
                ex.printStackTrace();
            }

            // Do we need to display the user manual?
            if (cmd.hasOption("?") || cmd.hasOption("help")) {
                formatter.printHelp("[OPTIONS]", options);
                return;
            }
            config = new Configuration();
            parseCommandOptions(cmd, config);
            if (Configuration.enableScheduler) {
                SchedulerV2.initialize();
                Configuration.orgProjectPath = config.projectPath;
            }

            // if config.targetCase is dir, list all java files in it and run them one by one. Otherwise, run the single java file.
            if (Configuration.enableScheduler && FS.isPathDir(config.targetCase)) {
                List<String> javaFiles = FS.listAllFiles(config.targetCase);
                String seedPath = javaFiles.get(SchedulerV2.selectSeed(javaFiles));
                config.fullSrcName = seedPath;
                config.projectPath = FS.getParentDirString(seedPath);
                config.targetCase = FS.getStem(seedPath);
                SchedulerV2.tryLockUsingSeed(seedPath);
            }

            config.settingAll();

            Scheduler scheduler = new Scheduler(config);
            scheduler.run();
        } catch (Exception e) {
            System.err.printf("The analysis has failed. Error message: %s\n", e.getMessage());
            e.printStackTrace();
        }
        if (Configuration.enableScheduler && config != null && config.fullSrcName != null && !Scheduler.hasScore) {
            SchedulerV2.scoreTestCase(null, null, config.fullSrcName, Scheduler.selMutNames, null, 1);
        }
    }


    private void initializeCommandLineOptions() {
        options.addOption("?", "help", false, "Print this help message");
        options.addOption(PROJECT_PATH, true, "source code root path. e.g., /path/to/buggyProject. Necessary.");
        options.addOption(TARGET_CASE, true, "target java file. e.g., a.b.c denotes a/b/c.java. Necessary.");
        options.addOption(JDK, true, "the jdk directory, path to bin/. At least one JDK. We will check whether" +
                " the outputs of these JDKs are consistant. Necessary.");
        options.addOption(LINE_NUMBER, true, "line number of target file to be mutated. e.g., 10. Default is null.");
        options.addOption(MAX_ITER, true, "max iteration times.. e.g., 100. Default is 10");
//        options.addOption(RANDOM_OPTION, true, "disable random options? e.g., true. Default is false");
        options.addOption(USE_JTREG, true, "use Jtreg? e.g., true. Default is false");
        options.addOption(USE_RANDOM_GC_OPTIONS, true, "use random GC options? e.g., true. Default is false");
        options.addOption(USE_RANDOM_JIT_OPTIONS, true, "use random JIT options? e.g., true. Default is false");
        options.addOption(ENABLE_PROFILE_GUIDE, true, "enable profile guidance? We recommand false" +
                " for faster testing. Default is false.");
        options.addOption(RCOV_JAVA_HOME, true, "the java home for RCOV. e.g., /path/to/rcov-java-home. Default is null");
        options.addOption(ENABLE_SCHEDULER, true, "enable scheduler? e.g., true. Default is false");
        options.addOption(ENABLE_RCOV_RUN_ANY_WAY, true, "enable rcov run any way? e.g., true. Default is false");
    }

    protected void parseCommandOptions(CommandLine cmd, Configuration config) throws IOException {
        String project_path = cmd.getOptionValue(PROJECT_PATH);
        if (project_path != null) {
            config.projectPath = project_path;
        } else throw new IOException("project_path is null");

        String target_case = cmd.getOptionValue(TARGET_CASE);
        if (target_case != null) {
            config.targetCase = target_case;
        } else throw new IOException("target_case is null");

        String jdk = cmd.getOptionValue(JDK);
        if (jdk != null) {
            config.JDKRoot = jdk;
        } else throw new IOException("jdk is null");

        String line_number = cmd.getOptionValue(LINE_NUMBER);
        if (line_number != null) {
            Configuration.lineNumber = Integer.parseInt(line_number);
        } else Configuration.lineNumber = -1;

        String max_iter = cmd.getOptionValue(MAX_ITER);
        if (max_iter != null) {
            Configuration.maxIter = Integer.parseInt(max_iter);
        } else Configuration.maxIter = 50;

//        String random_option = cmd.getOptionValue(RANDOM_OPTION);
//        if (random_option != null) {
//            Configuration.disableRandomOptions = Boolean.parseBoolean(random_option);
//        } else Configuration.disableRandomOptions = false;

        String enable_profile_guidance = cmd.getOptionValue(ENABLE_PROFILE_GUIDE);
        if (enable_profile_guidance != null) {
            Configuration.enableProfileGuidance = Boolean.parseBoolean(enable_profile_guidance);
        } else Configuration.enableProfileGuidance = false;

        String useJtreg = cmd.getOptionValue(USE_JTREG);
        if (useJtreg != null) {
            Configuration.usejTreg = Boolean.parseBoolean(useJtreg);
        } else Configuration.usejTreg = false;

        String useGCOption = cmd.getOptionValue(USE_RANDOM_GC_OPTIONS);
        if (useGCOption != null) {
            Configuration.useRandomGCOptions = Boolean.parseBoolean(useGCOption);
        } else Configuration.useRandomGCOptions = false;

        String useJITOption = cmd.getOptionValue(USE_RANDOM_JIT_OPTIONS);
        if (useJITOption != null) {
            Configuration.useRandomJITOptions = Boolean.parseBoolean(useJITOption);
        } else Configuration.useRandomJITOptions = false;

        String rcovHome = cmd.getOptionValue(RCOV_JAVA_HOME);
        if (rcovHome != null) {
            Configuration.rcovJavaHome = rcovHome;
        } else Configuration.rcovJavaHome = null;

        String enableScheduler = cmd.getOptionValue(ENABLE_SCHEDULER);
        if (enableScheduler != null) {
            Configuration.enableScheduler = Boolean.parseBoolean(enableScheduler);
        } else Configuration.enableScheduler = false;

        String enableRcovRunAnyWay = cmd.getOptionValue(ENABLE_RCOV_RUN_ANY_WAY);
        if (enableRcovRunAnyWay != null) {
            Configuration.enableRcovRunAnyWay = Boolean.parseBoolean(enableRcovRunAnyWay);
        } else Configuration.enableRcovRunAnyWay = false;

    }

}
