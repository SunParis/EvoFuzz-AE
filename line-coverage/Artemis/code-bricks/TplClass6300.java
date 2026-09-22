import java.util.zip.GZIPInputStream;

public class TplClass6300 {

    private static final void method(byte[] result, byte[] buf, java.util.zip.GZIPInputStream gzis, int off, int n) throws Throwable {
        while ((n = gzis.read(buf, 0, buf.length)) != -1) {
            System.arraycopy(buf, 0, result, off, n);
            off += n;
            // no range check, if overflow, let it fail
        }
    }
}

