import java.text.NumberFormat;

public class TplClass5746 {

    private static final void method(java.text.NumberFormat nf) throws Throwable {
        try {
            nf.setRoundingMode(null);
        } catch (NullPointerException npe) {
            // continue testing
        }
    }
}

