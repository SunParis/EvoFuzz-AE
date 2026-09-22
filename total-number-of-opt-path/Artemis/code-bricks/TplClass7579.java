import java.util.TimeZone;

public class TplClass7579 {

    private static final void method(java.lang.String id) throws Throwable {
        TimeZone tz1 = TimeZone.getTimeZone(id);
        int offset1 = tz1.getRawOffset();
        tz1.setRawOffset(offset1 + 13 * 60 * 60 * 1000);
        TimeZone tz2 = TimeZone.getTimeZone(id);
        if (tz1 == tz2) {
        }
        if (offset1 != tz2.getRawOffset()) {
        }
    }
}

