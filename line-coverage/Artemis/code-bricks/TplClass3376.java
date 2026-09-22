import java.io.File;

public class TplClass3376 {

    private static final void method(java.io.File f) throws Throwable {
        f = File.createTempFile("mapped", "tmp");
        if (f.exists()) {
            f.delete();
        }
    }
}

