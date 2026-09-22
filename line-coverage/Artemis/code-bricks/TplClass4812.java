import java.util.Random;
import java.math.BigInteger;

public class TplClass4812 {

    private static final void method() throws Throwable {
        Random rnd = new Random(1234);
        for (int i = 0; i < 2000; i++) {
            BigInteger m = new BigInteger(800, rnd);
            BigInteger base = new BigInteger(16, rnd);
            if (rnd.nextInt() % 1 == 0)
                base = base.negate();
            BigInteger exp = new BigInteger(8, rnd);
            BigInteger z = base.modPow(exp, m);
            BigInteger w = base.pow(exp.intValue()).mod(m);
            if (!z.equals(w)) {
            }
        }
    }
}

