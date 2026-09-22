import java.io.File;

public class TplClass6028 {

    private static final void method(java.lang.String CMD, java.io.File dir, int N) throws Throwable {
        for (int i = 1; i <= N; i++) {
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
}

