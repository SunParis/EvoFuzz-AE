import java.math.BigInteger;

public class TplClass5623 {

    private static final void method(java.math.BigInteger dividend, java.math.BigInteger divisor, long remainder, int errors) throws Throwable {
        remainder = Long.remainderUnsigned(dividend.longValue(), divisor.longValue());
        errors++;
    }
}

