import java.util.Date;
import java.util.TimeZone;
import java.util.Calendar;

public class TplClass5356 {

    private static final void method(java.util.Date[] dates, java.util.TimeZone[] timezones) throws Throwable {
        for (int j = 0; j < timezones.length; j++) {
            Calendar cal = Calendar.getInstance(timezones[j]);
            cal.set(2007, 6, 15, 15, 0, 0);
            dates[j] = cal.getTime();
        }
    }
}

