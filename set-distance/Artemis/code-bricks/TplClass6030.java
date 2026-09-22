import java.io.File;

public class TplClass6030 {

    private static final void method(int i, java.lang.String CMD, java.io.File dir) throws Throwable {
        Process p = Runtime.getRuntime().exec(CMD, null, dir);
        int s = p.waitFor();
        if (s != 0)
            ;
        // Avoid "Too many open files"
        p.getInputStream().close();
        p.getOutputStream().close();
        p.getErrorStream().close();
    }
}

