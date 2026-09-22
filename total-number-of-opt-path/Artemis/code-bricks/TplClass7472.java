import java.math.BigInteger;
import java.math.BigDecimal;

public class TplClass7472 {

    private static final void method(java.math.BigDecimal bd, java.lang.String s) throws Throwable {
        s = s.substring(2);
        int indP = s.indexOf('p');
        long exp = Long.parseLong(s.substring(indP + 1));
        int indD = s.indexOf('.');
        String significand;
        if (indD >= 0) {
            significand = s.substring(0, indD) + s.substring(indD + 1, indP);
            exp -= 4 * (indP - indD - 1);
        } else {
            significand = s.substring(0, indP);
        }
        bd = new BigDecimal(new BigInteger(significand, 16));
        if (exp >= 0) {
            bd = bd.multiply(BigDecimal.valueOf(2).pow((int) exp));
        } else {
            bd = bd.divide(BigDecimal.valueOf(2).pow((int) -exp));
        }
    }
}

