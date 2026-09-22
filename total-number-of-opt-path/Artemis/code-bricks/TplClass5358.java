import java.util.TimeZone;
import java.util.Locale;

public class TplClass5358 {

    private static final void method(java.lang.String date, java.lang.String date_LA, java.util.Locale[] locales, java.util.TimeZone[] timezones, boolean err, java.lang.String[] expected, int i, int j) throws Throwable {
        if (!expected[j].equals(date_LA)) {
            err = true;
        }
    }
}

