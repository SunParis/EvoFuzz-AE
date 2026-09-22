import java.util.Locale;
import java.text.DecimalFormat;

public class TplClass4936 {

    private static final void method() throws Throwable {
        DecimalFormat nf = (DecimalFormat) DecimalFormat.getPercentInstance(Locale.US);
        nf.setMaximumFractionDigits(3);
        nf.setMinimumFractionDigits(0);
        nf.setMultiplier(1);
        double d = 0.005678;
        String result = nf.format(d);
        if (!result.equals("0.006%")) {
        }
        d = 0.00;
        result = nf.format(d);
        if (!result.equals("0%")) {
        }
        d = 0.005678;
        result = nf.format(d);
        if (!result.equals("0.006%")) {
        }
        // checking with the non zero value
        d = 0.005678;
        result = nf.format(d);
        if (!result.equals("0.006%")) {
        }
        d = 9.00;
        result = nf.format(d);
        if (!result.equals("9%")) {
        }
        d = 0.005678;
        result = nf.format(d);
        if (!result.equals("0.006%")) {
        }
    }
}

