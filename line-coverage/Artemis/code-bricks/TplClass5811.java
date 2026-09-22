import java.util.Random;
import java.io.InputStream;

public class TplClass5811 {

    private static final void method(java.util.Random rand, int total, java.io.InputStream in, int hash) throws Throwable {
        int n;
        do {
            // random offset/len
            byte[] buf = new byte[128 + rand.nextInt(128)];
            int len, off;
            if (rand.nextBoolean()) {
                len = buf.length;
                off = 0;
                n = in.read(buf);
            } else {
                len = 1 + rand.nextInt(64);
                off = rand.nextInt(64);
                n = in.read(buf, off, len);
            }
            if (n > len)
                ;
            if (n > 0) {
                total += n;
                for (int i = 0; i < n; i++) {
                    int value = buf[off + i];
                    hash = hash ^ value;
                }
            }
        } while (n > 0);
        in.close();
    }
}

