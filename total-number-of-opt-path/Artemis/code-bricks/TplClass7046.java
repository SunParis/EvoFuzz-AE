import java.util.Random;
import java.math.BigInteger;
import java.math.BigDecimal;

public class TplClass7046 {

    private static final void method(int seed, java.util.Random rnd) throws Throwable {
        int size = rnd.nextInt(100) + 1;
        BigInteger bi = new BigInteger(size, rnd);
        if (rnd.nextBoolean())
            bi = bi.negate();
        int decimalLength = bi.toString().length();
        int scale = rnd.nextInt(decimalLength);
        BigDecimal bd = new BigDecimal(bi, scale);
        String bdString = bd.toString();
        // System.err.println("bd string: " + bdString);
        BigDecimal bdDoppel = new BigDecimal(bdString);
        if (!bd.equals(bdDoppel)) {
        }
    }
}

