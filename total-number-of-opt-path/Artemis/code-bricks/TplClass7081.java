import java.math.BigDecimal;

public class TplClass7081 {

    private static final void method(int preferredScale, java.math.BigDecimal result, int failures, java.math.BigDecimal element) throws Throwable {
        if (result.scale() != preferredScale || result.compareTo(element.negate()) != 0) {
            failures++;
        }
    }
}

