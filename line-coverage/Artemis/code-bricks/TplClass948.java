import java.util.BitSet;

public class TplClass948 {

    private static final void method(java.util.BitSet bs, int t, int i, int bits_set, long bit, long[] ra) throws Throwable {
        if ((ra[t] & bit) != 0) {
            bs.set(i);
            bits_set++;
        }
    }
}

