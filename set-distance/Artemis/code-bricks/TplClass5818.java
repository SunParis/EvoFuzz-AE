import java.util.Random;
import java.io.OutputStream;

public class TplClass5818 {

    private static final void method(java.util.Random rand, int rem, int hash, java.io.OutputStream out) throws Throwable {
        do {
            byte[] buf = new byte[1 + rand.nextInt(rem)];
            int off, len;
            // write random bytes
            if (rand.nextBoolean()) {
                off = 0;
                len = buf.length;
            } else {
                off = rand.nextInt(buf.length);
                int r = buf.length - off;
                len = (r <= 1) ? 1 : (1 + rand.nextInt(r));
            }
            for (int i = 0; i < len; i++) {
                byte value = (byte) rand.nextInt(256);
                buf[off + i] = value;
                hash = hash ^ value;
            }
            if ((off == 0) && (len == buf.length)) {
                out.write(buf);
            } else {
                out.write(buf, off, len);
            }
            rem -= len;
        } while (rem > 0);
        // close stream when done
        out.close();
    }
}

