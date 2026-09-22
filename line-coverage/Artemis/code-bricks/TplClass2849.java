import java.math.BigInteger;

public class TplClass2849 {

    private static final void method(int nIters, java.math.BigInteger huge) throws Throwable {
        for (int i = 0; i < nIters; ++i) {
            // 10 GB total
            huge = huge.add(BigInteger.ONE);
        }
    }
}

