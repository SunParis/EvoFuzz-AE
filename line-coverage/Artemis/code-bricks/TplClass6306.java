import java.util.Arrays;
import java.util.zip.GZIPInputStream;

public class TplClass6306 {

    private static final void method(byte[] src, int readBufSize, java.util.zip.GZIPInputStream gzis) throws Throwable {
        byte[] result = new byte[src.length + 10];
        byte[] buf = new byte[readBufSize];
        int n = 0;
        int off = 0;
        while ((n = gzis.read(buf, 0, buf.length)) != -1) {
            System.arraycopy(buf, 0, result, off, n);
            off += n;
            // no range check, if overflow, let it fail
        }
        if (off != src.length || gzis.available() != 0 || !Arrays.equals(src, Arrays.copyOf(result, off))) {
        }
    }
}

