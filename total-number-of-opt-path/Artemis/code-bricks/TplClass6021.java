import java.io.File;

public class TplClass6021 {

    private static final void method(java.lang.Process p, java.lang.String[] cmdarray, boolean flag, java.lang.String cmd, java.io.File f) throws Throwable {
        if (flag) {
            p = Runtime.getRuntime().exec(cmd, null, f);
        } else {
            p = Runtime.getRuntime().exec(cmdarray, null, f);
        }
    }
}

