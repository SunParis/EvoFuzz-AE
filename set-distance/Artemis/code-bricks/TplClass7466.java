import java.math.BigDecimal;

public class TplClass7466 {

    private static final void method(long exp, java.math.BigDecimal bd) throws Throwable {
        if (exp >= 0) {
            bd = bd.multiply(BigDecimal.valueOf(2).pow((int) exp));
        } else {
            bd = bd.divide(BigDecimal.valueOf(2).pow((int) -exp));
        }
    }
}

