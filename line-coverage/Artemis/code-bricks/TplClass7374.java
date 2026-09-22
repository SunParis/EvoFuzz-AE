import java.util.TimeZone;
import java.time.ZoneId;

public class TplClass7374 {

    private static final void method(java.lang.String zidStr, java.util.TimeZone tz) throws Throwable {
        // no round-trip for alias and "GMT"
        if (!tz.equals(TimeZone.getTimeZone(tz.toZoneId())) && !ZoneId.SHORT_IDS.containsKey(zidStr) && !zidStr.startsWith("GMT")) {
        }
    }
}

