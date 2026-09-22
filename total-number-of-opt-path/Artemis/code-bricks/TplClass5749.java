import java.text.NumberFormat;
import java.math.RoundingMode;
import java.text.ChoiceFormat;

public class TplClass5749 {

    private static final void method() throws Throwable {
        NumberFormat nf = NumberFormat.getIntegerInstance();
        if (nf.getRoundingMode() != RoundingMode.HALF_EVEN) {
        }
        try {
            nf.setRoundingMode(null);
        } catch (NullPointerException npe) {
            // continue testing
        }
        ChoiceFormat cf = new ChoiceFormat("");
        try {
            cf.setRoundingMode(RoundingMode.HALF_EVEN);
        } catch (UnsupportedOperationException uoe) {
            // continue testing
        }
        try {
            cf.getRoundingMode();
        } catch (UnsupportedOperationException uoe) {
            // continue testing
        }
    }
}

