import java.util.Random;

public class TplClass5807 {

    private static final void method(java.util.Random rand, byte[] buf, int len, int off) throws Throwable {
        // write random bytes
        if (rand.nextBoolean()) {
            off = 0;
            len = buf.length;
        } else {
            off = rand.nextInt(buf.length);
            int r = buf.length - off;
            len = (r <= 1) ? 1 : (1 + rand.nextInt(r));
        }
    }
}

