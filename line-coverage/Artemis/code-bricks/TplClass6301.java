import java.util.Arrays;
import java.util.zip.GZIPInputStream;

public class TplClass6301 {

    private static final void method(byte[] result, byte[] src, java.util.zip.GZIPInputStream gzis, int off) throws Throwable {
        if (off != src.length || gzis.available() != 0 || !Arrays.equals(src, Arrays.copyOf(result, off))) {
        }
    }
}

