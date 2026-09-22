import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.TimeZone;

public class TplClass7562 {

    private static final void method(java.lang.String[] TimeZoneIds, int i) throws Throwable {
        TimeZone tz = TimeZone.getTimeZone(TimeZoneIds[i]);
        Calendar calendar = new GregorianCalendar(tz);
        String calString = calendar.toString();
    }
}

