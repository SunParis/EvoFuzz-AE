import java.util.BitSet;

public class TplClass945 {

    private static final void method(java.util.BitSet bs, int bits_set, int NBITS, long[] ra) throws Throwable {
        for (int i = 0, t = 0, b = 0; i < NBITS; i++) {
            long bit = 1L << b++;
            if ((ra[t] & bit) != 0) {
                bs.set(i);
                bits_set++;
            }
            if (b == 64) {
                t++;
                b = 0;
            }
        }
    }
}

