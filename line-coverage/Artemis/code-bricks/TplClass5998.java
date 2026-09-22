import java.io.InputStream;
import java.io.OutputStream;

public class TplClass5998 {

    private static final void method(java.io.InputStream in, java.io.OutputStream out) throws Throwable {
        int c;
        byte[] buf = new byte[8192];
        int n;
        while ((n = in.read(buf)) != -1) {
            out.write(buf, 0, n);
            out.flush();
        }
    }
}

