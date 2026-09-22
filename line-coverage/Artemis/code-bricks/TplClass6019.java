import java.io.File;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class TplClass6019 {

    private static final void method(java.io.File[] files, java.lang.String[] cmdarray, boolean flag, java.lang.String cmd) throws Throwable {
        for (int i = 0; i < files.length; i++) {
            File f = files[i];
            if (f.isDirectory() && (new File(f, "SetCwd.class")).exists()) {
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
    }
}

