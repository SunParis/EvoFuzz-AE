import java.io.File;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class TplClass6025 {

    private static final void method(java.lang.String[] cmdarray, boolean flag, java.lang.String cmd, java.io.File f) throws Throwable {
        String newDir = f.getCanonicalPath();
        // exec a new SetCwd in the sub directory
        Process p = null;
        if (flag) {
            p = Runtime.getRuntime().exec(cmd, null, f);
        } else {
            p = Runtime.getRuntime().exec(cmdarray, null, f);
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
        // Read back output from child
        String s = in.readLine();
        if (!s.startsWith(newDir)) {
        }
        // Join on the child
        p.waitFor();
    }
}

