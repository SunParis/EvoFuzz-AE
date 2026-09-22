import java.util.Locale;
import java.text.DateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.Calendar;

public class TplClass3591 {

    private static final void method() throws Throwable {
        TimeZone tz = TimeZone.getTimeZone("GMT");
        Locale usa = new Locale("en", "US");
        Calendar usaCal = Calendar.getInstance(tz, usa);
        // don't want current date/time
        usaCal.clear();
        usaCal.set(2012, Calendar.JANUARY, 1);
        Date when = usaCal.getTime();
        DateFormat fmt = DateFormat.getDateInstance(DateFormat.FULL, usa);
        // defaults to local TZ; force GMT
        fmt.setTimeZone(tz);
        Locale france = new Locale("fr", "FR");
        Calendar franceCal = Calendar.getInstance(tz, france);
        franceCal.clear();
        franceCal.set(2012, Calendar.JANUARY, 2);
        when = franceCal.getTime();
        fmt = DateFormat.getDateInstance(DateFormat.FULL, usa);
        // defaults to local TZ; force GMT
        fmt.setTimeZone(tz);
    }
}

