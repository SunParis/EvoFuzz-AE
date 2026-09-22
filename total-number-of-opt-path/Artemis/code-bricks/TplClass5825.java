import java.io.OutputStream;

public class TplClass5825 {

    private static final void method(byte[] buf, int expected, java.io.OutputStream out) throws Throwable {
        for (int i = 0; i < buf.length; i++) {
            int len = buf.length - i;
            out.write(buf, i, len);
            expected += len;
        }
    }
}

