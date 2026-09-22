import java.math.RoundingMode;

public class TplClass6148 {

    private static final void method() throws Throwable {
        for (RoundingMode rm : RoundingMode.values()) {
            if (rm != RoundingMode.valueOf(rm.toString())) {
            }
        }
    }
}

