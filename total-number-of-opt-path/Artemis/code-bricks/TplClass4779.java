import java.math.BigInteger;

public class TplClass4779 {

    private static final void method() throws Throwable {
        BigInteger a = BigInteger.ONE.shiftLeft(2147483646);
        BigInteger b = BigInteger.ONE.shiftLeft(1568);
        BigInteger[] qr = a.divideAndRemainder(b);
        BigInteger q = qr[0];
        BigInteger r = qr[1];
        if (!r.equals(BigInteger.ZERO))
            ;
        if (q.bitLength() != 2147482079)
            ;
    }
}

