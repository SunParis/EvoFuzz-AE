import java.util.TimeZone;
import java.util.Date;
import java.util.Locale;
import java.text.SimpleDateFormat;

public class TplClass5360 {

    private static final void method(java.util.Locale[] locales, java.util.TimeZone timezone_LA, java.util.TimeZone[] timezones, boolean err, java.text.SimpleDateFormat sdf, java.lang.String[] expected, int i, int j, java.util.Date[] dates) throws Throwable {
        sdf.setTimeZone(timezones[j]);
        String date = sdf.format(dates[j]);
        sdf.setTimeZone(timezone_LA);
        String date_LA = sdf.parse(date).toString();
        if (!expected[j].equals(date_LA)) {
            err = true;
        }
    }
}

