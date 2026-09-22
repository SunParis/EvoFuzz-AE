import java.io.InputStream;

public class TplClass6310 {

    private static final void method(byte[] buf, java.io.InputStream s, int pos, int n) throws Throwable {
        while ((n = s.read(buf, pos, buf.length - pos)) > 0) pos += n;
    }
}

