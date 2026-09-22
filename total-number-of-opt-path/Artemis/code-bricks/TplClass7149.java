import java.math.BigDecimal;

public class TplClass7149 {

    private static final void method(java.math.BigDecimal bd, float f) throws Throwable {
        float fbd = bd.floatValue();
        if (f != fbd) {
            String message = String.format("Bad conversion:" + "got %g (%a)\texpected %g (%a)", f, f, fbd, fbd);
        }
    }
}

