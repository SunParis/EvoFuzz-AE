import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class TplClass5732 {

    private static final void method() throws Throwable {
        DecimalFormat df = (DecimalFormat) NumberFormat.getInstance(Locale.JAPAN);
        DecimalFormatSymbols dfs = df.getDecimalFormatSymbols();
        if (dfs.getPatternSeparator() != ';') {
        }
    }
}

