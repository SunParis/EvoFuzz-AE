import java.math.BigDecimal;

public class TplClass7151 {

    private static final void method(double d, java.math.BigDecimal bd) throws Throwable {
        double dbd = bd.doubleValue();
        if (d != dbd) {
            String message = String.format("Bad conversion:" + "got %g (%a)\texpected %g (%a)", d, d, dbd, dbd);
        }
    }
}

