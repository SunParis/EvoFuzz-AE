import java.util.Random;
import java.io.InputStream;

public class TplClass5814 {

    private static final void method(java.util.Random rand, byte[] buf, int len, java.io.InputStream in, int n, int off) throws Throwable {
        len = 1 + rand.nextInt(64);
        off = rand.nextInt(64);
        n = in.read(buf, off, len);
    }
}

