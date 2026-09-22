import java.io.InputStream;

public class TplClass6317 {

    private static final void method(byte[] buf, java.io.InputStream s) throws Throwable {
        int pos = 0;
        int n;
        while ((n = s.read(buf, pos, buf.length - pos)) > 0) pos += n;
        if (pos != buf.length)
            ;
    }
}

