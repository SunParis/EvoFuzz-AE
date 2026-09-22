import java.io.File;

public class TplClass3639 {

    private static final void method(java.io.File dumpFile, java.io.File hprof_conv, java.io.File convFile) throws Throwable {
        ProcessBuilder pb = new ProcessBuilder(hprof_conv.getAbsoluteFile().toString(), dumpFile.getAbsoluteFile().toString(), convFile.getAbsoluteFile().toString());
        pb.redirectErrorStream(true);
        Process process = pb.start();
        int ret = process.waitFor();
        if (ret != 0) {
        }
    }
}

