import java.math.BigInteger;

public class TplClass4782 {

    private static final void method(java.math.BigInteger[] inRange, int errors) throws Throwable {
        for (BigInteger bi : inRange) {
            if (bi.intValueExact() != bi.intValue()) {
                errors++;
            }
        }
    }
}

