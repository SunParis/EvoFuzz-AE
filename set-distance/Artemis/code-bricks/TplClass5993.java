import java.io.InputStream;
import java.io.OutputStream;

public class TplClass5993 {

    private static final void method(byte[] buf, java.io.InputStream in, int n, java.io.OutputStream out) throws Throwable {
        while ((n = in.read(buf)) != -1) {
            out.write(buf, 0, n);
            out.flush();
        }
    }
}

