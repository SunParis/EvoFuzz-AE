import java.math.BigDecimal;

public class TplClass7467 {

    private static final void method(java.math.BigDecimal HALF, float na, java.math.BigDecimal l, java.math.BigDecimal u, float n) throws Throwable {
        if (Float.isInfinite(na)) {
            l = new BigDecimal(Float.MAX_VALUE).add(new BigDecimal(Math.ulp(Float.MAX_VALUE)).multiply(HALF));
            u = null;
        } else {
            l = new BigDecimal(na).subtract(new BigDecimal(Math.ulp(-Math.nextUp(-na))).multiply(HALF));
            u = new BigDecimal(na).add(new BigDecimal(Math.ulp(n)).multiply(HALF));
        }
    }
}

