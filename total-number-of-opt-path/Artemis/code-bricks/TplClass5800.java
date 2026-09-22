import java.util.Random;

public class TplClass5800 {

    private static final void method(java.util.Random rand, byte[] buf, int len, int hash, int off) throws Throwable {
        for (int i = 0; i < len; i++) {
            byte value = (byte) rand.nextInt(256);
            buf[off + i] = value;
            hash = hash ^ value;
        }
    }
}

