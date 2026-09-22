import java.math.BigInteger;

public class TplClass2852 {

    private static final void method() throws Throwable {
        // Presumed < 1_000_000.
        final int nIters = 20_000;
        // 2^20
        final BigInteger big2_20 = BigInteger.valueOf(1024 * 1024);
        // ~0.5MB
        BigInteger huge = BigInteger.valueOf(1).shiftLeft(4_000_000);
        for (int i = 0; i < nIters; ++i) {
            // 10 GB total
            huge = huge.add(BigInteger.ONE);
        }
        if (huge.bitLength() != 4_000_001) {
        } else if (huge.mod(big2_20).compareTo(BigInteger.valueOf(nIters)) != 0) {
        } else {
        }
    }
}

