import java.io.File;

public class TplClass3640 {

    private static final void method(java.io.File convFile, java.io.File dumpFile) throws Throwable {
        // Delete the files.
        if (dumpFile != null) {
            dumpFile.delete();
        }
        if (convFile != null) {
            convFile.delete();
        }
    }
}

