import java.math.BigInteger;

public class TplClass4780 {

    private static final void method(java.math.BigInteger[] inRange, int errors) throws Throwable {
        for (BigInteger bi : inRange) {
            if (bi.longValueExact() != bi.longValue()) {
                errors++;
            }
        }
    }
}

