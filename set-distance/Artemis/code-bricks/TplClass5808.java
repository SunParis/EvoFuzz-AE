import java.io.OutputStream;

public class TplClass5808 {

    private static final void method(byte[] buf, int len, int off, java.io.OutputStream out) throws Throwable {
        if ((off == 0) && (len == buf.length)) {
            out.write(buf);
        } else {
            out.write(buf, off, len);
        }
    }
}

