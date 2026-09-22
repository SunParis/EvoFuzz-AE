import java.util.Random;

public class TplClass964 {

    private static final void method(long[] result, java.util.Random r, int entries, long[] dest, boolean[] src) throws Throwable {
        for (int i = 0; i < entries; i++) {
            long l = r.nextLong();
            for (int bit = 0; bit < 64; bit++) {
                src[i * 64 + bit] = (l & (1L << bit)) != 0;
            }
            dest[i] = 0;
            result[i] = l;
        }
    }
}

