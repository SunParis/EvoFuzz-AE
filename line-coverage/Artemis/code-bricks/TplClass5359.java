import java.util.Date;
import java.util.TimeZone;
import java.util.Calendar;

public class TplClass5359 {

    private static final void method(java.util.Date[] dates, int j, java.util.TimeZone[] timezones) throws Throwable {
        Calendar cal = Calendar.getInstance(timezones[j]);
        cal.set(2007, 6, 15, 15, 0, 0);
        dates[j] = cal.getTime();
    }
}

