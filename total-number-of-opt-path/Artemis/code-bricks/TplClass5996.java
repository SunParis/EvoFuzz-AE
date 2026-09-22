import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

public class TplClass5996 {

    private static final void method(java.io.InputStream in, java.io.OutputStream out) throws Throwable {
        try {
            int c;
            byte[] buf = new byte[8192];
            int n;
            while ((n = in.read(buf)) != -1) {
                out.write(buf, 0, n);
                out.flush();
            }
            /*
                while ((c = in.read()) != -1) {
                    out.write(c);
                    if (c == '\n')
                        out.flush();
                }
                out.flush();
                */
        } catch (IOException e) {
        } finally {
            if (!System.out.equals(out) && !System.err.equals(out)) {
                // Note: in order to get an exec'd java process to
                // see EOF on input, it is necessary to close stdin
                if (out != null) {
                    try {
                        out.close();
                    } catch (Exception e) {
                    }
                }
            }
        }
    }
}

