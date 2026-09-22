import java.math.BigDecimal;

public class TplClass7479 {

    private static final void method(java.math.BigDecimal HALF, float na, java.math.BigDecimal l, java.math.BigDecimal u, float n) throws Throwable {
        l = new BigDecimal(na).subtract(new BigDecimal(Math.ulp(-Math.nextUp(-na))).multiply(HALF));
        u = new BigDecimal(na).add(new BigDecimal(Math.ulp(n)).multiply(HALF));
    }
}

