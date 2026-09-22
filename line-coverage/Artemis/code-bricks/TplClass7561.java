import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.TimeZone;

public class TplClass7561 {

    private static final void method() throws Throwable {
        Date date = new Date();
        String[] TimeZoneIds = TimeZone.getAvailableIDs();
        for (int i = 0; i < TimeZoneIds.length; i++) {
            TimeZone tz = TimeZone.getTimeZone(TimeZoneIds[i]);
            Calendar calendar = new GregorianCalendar(tz);
            String calString = calendar.toString();
        }
    }
}

