import java.math.BigDecimal;

public class TplClass7478 {

    private static final void method(java.math.BigDecimal HALF, java.math.BigDecimal l, java.math.BigDecimal u) throws Throwable {
        l = new BigDecimal(Float.MAX_VALUE).add(new BigDecimal(Math.ulp(Float.MAX_VALUE)).multiply(HALF));
        u = null;
    }
}

