import java.math.RoundingMode;
import java.math.BigDecimal;

public class TplClass6157 {

    private static final void method() throws Throwable {
        for (RoundingMode rm : RoundingMode.values()) {
            if (rm != RoundingMode.valueOf(rm.toString())) {
            }
        }
        // Test that mapping of old integers to new values is correct
        if (RoundingMode.valueOf(BigDecimal.ROUND_CEILING) != RoundingMode.CEILING) {
        }
        if (RoundingMode.valueOf(BigDecimal.ROUND_DOWN) != RoundingMode.DOWN) {
        }
        if (RoundingMode.valueOf(BigDecimal.ROUND_FLOOR) != RoundingMode.FLOOR) {
        }
        if (RoundingMode.valueOf(BigDecimal.ROUND_HALF_DOWN) != RoundingMode.HALF_DOWN) {
        }
        if (RoundingMode.valueOf(BigDecimal.ROUND_HALF_EVEN) != RoundingMode.HALF_EVEN) {
        }
        if (RoundingMode.valueOf(BigDecimal.ROUND_HALF_UP) != RoundingMode.HALF_UP) {
        }
        if (RoundingMode.valueOf(BigDecimal.ROUND_UNNECESSARY) != RoundingMode.UNNECESSARY) {
        }
    }
}

