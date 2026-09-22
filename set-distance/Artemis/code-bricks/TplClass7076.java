import java.math.MathContext;
import java.math.BigDecimal;

public class TplClass7076 {

    private static final void method(java.math.BigDecimal zero, int failures, java.math.MathContext mc, java.math.BigDecimal element) throws Throwable {
        if (Math.abs((long) zero.scale()) < 100) {
            int preferredScale = Math.max(zero.scale(), element.scale());
            if (mc.getPrecision() != 0) {
                if (preferredScale < -4)
                    preferredScale = -4;
                else if (preferredScale > -(5 - mc.getPrecision())) {
                    preferredScale = -(5 - mc.getPrecision());
                }
            }
            /*
                      System.err.println("\n    " + element + " +\t" + zero + " =\t" + result);

                      System.err.println("scales" + element.scale() + " \t" + zero.scale() +
                      "  \t " + result.scale() + "\t precison = " + mc.getPrecision());
                      System.err.println("expected scale = " + preferredScale);
                    */
            BigDecimal result = element.add(zero, mc);
            if (result.scale() != preferredScale || result.compareTo(element) != 0) {
                failures++;
            }
            result = zero.add(element, mc);
            if (result.scale() != preferredScale || result.compareTo(element) != 0) {
                failures++;
            }
            result = element.negate().add(zero, mc);
            if (result.scale() != preferredScale || result.compareTo(element.negate()) != 0) {
                failures++;
            }
            result = zero.add(element.negate(), mc);
            if (result.scale() != preferredScale || result.compareTo(element.negate()) != 0) {
                failures++;
            }
        }
    }
}

