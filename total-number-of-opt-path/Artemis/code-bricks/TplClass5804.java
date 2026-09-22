import java.util.Random;
import java.io.InputStream;

public class TplClass5804 {

    private static final void method(java.util.Random rand, byte[] buf, int len, java.io.InputStream in, int n, int off) throws Throwable {
        if (rand.nextBoolean()) {
            len = buf.length;
            off = 0;
            n = in.read(buf);
        } else {
            len = 1 + rand.nextInt(64);
            off = rand.nextInt(64);
            n = in.read(buf, off, len);
        }
    }
}

