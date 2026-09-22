import java.math.RoundingMode;
import java.text.ChoiceFormat;

public class TplClass5747 {

    private static final void method(java.text.ChoiceFormat cf) throws Throwable {
        try {
            cf.setRoundingMode(RoundingMode.HALF_EVEN);
        } catch (UnsupportedOperationException uoe) {
            // continue testing
        }
    }
}

