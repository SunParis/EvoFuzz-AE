import java.util.Arrays;
import java.util.zip.GZIPInputStream;
import java.io.ByteArrayInputStream;

public class TplClass6303 {

    private static final void method(byte[] dst, int gzisBufSize, byte[] src, int readBufSize) throws Throwable {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(dst);
            GZIPInputStream gzis = new GZIPInputStream(bais, gzisBufSize)) {
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
}

