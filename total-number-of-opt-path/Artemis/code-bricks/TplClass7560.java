import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.TimeZone;

public class TplClass7560 {

    private static final void method(java.lang.String[] TimeZoneIds) throws Throwable {
        for (int i = 0; i < TimeZoneIds.length; i++) {
            TimeZone tz = TimeZone.getTimeZone(TimeZoneIds[i]);
            Calendar calendar = new GregorianCalendar(tz);
            String calString = calendar.toString();
        }
    }
}

