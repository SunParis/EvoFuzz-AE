import java.io.OutputStream;

public class TplClass5827 {

    private static final void method(int i, byte[] buf, int expected, java.io.OutputStream out) throws Throwable {
        int len = buf.length - i;
        out.write(buf, i, len);
        expected += len;
    }
}

