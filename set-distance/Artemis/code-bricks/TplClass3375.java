import java.io.File;

public class TplClass3375 {

    private static final void method(java.io.File f) throws Throwable {
        try {
            f = File.createTempFile("mapped", "tmp");
            if (f.exists()) {
                f.delete();
            }
        } catch (Exception e) {
            f = null;
        }
    }
}

