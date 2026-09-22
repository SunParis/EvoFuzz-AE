import java.math.MathContext;

public class TplClass7077 {

    private static final void method(int preferredScale, java.math.MathContext mc) throws Throwable {
        if (mc.getPrecision() != 0) {
            if (preferredScale < -4)
                preferredScale = -4;
            else if (preferredScale > -(5 - mc.getPrecision())) {
                preferredScale = -(5 - mc.getPrecision());
            }
        }
    }
}

