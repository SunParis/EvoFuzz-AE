import java.util.Random;

public class TplClass972 {

    private static final void method(long[] result, int i, java.util.Random r, long[] dest, boolean[] src) throws Throwable {
        long l = r.nextLong();
        for (int bit = 0; bit < 64; bit++) {
            src[i * 64 + bit] = (l & (1L << bit)) != 0;
        }
        dest[i] = 0;
        result[i] = l;
    }
}

