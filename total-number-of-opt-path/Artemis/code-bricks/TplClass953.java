import java.util.BitSet;

public class TplClass953 {

    private static final void method(java.util.BitSet bs, int NBITS, long[] ra) throws Throwable {
        bs.clear();
        int bits_set = 0;
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
        // Test Long.bitCount()
        int check_bits = bs.cardinality();
        if (check_bits != bits_set) {
            String bs_str = bs.toString();
        }
        // Test Long.numberOfTrailingZeros()
        check_bits = 0;
        for (int i = bs.nextSetBit(0); i >= 0; i = bs.nextSetBit(i + 1)) {
            check_bits++;
        }
        if (check_bits != bits_set) {
            String bs_str = bs.toString();
        }
        // Test Long.numberOfLeadingZeros()
        for (int i = bs.length(); i > 0; i = bs.length()) {
            bs.clear(i - 1);
        }
        // Test Long.bitCount()
        check_bits = bs.cardinality();
        if (check_bits != 0) {
            String bs_str = bs.toString();
        }
    }
}

