import java.util.Random;

public class TplClass5822 {

    private static final void method(java.util.Random rand, int i, byte[] buf, int hash, int off) throws Throwable {
        byte value = (byte) rand.nextInt(256);
        buf[off + i] = value;
        hash = hash ^ value;
    }
}

