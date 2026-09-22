import java.util.BitSet;

public class TplClass954 {

    private static final void method(java.util.BitSet bs, int b, int t, int i, int bits_set, long[] ra) throws Throwable {
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

