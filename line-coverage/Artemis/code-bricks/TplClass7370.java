import java.util.GregorianCalendar;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class TplClass7370 {

    private static final void method(int nanos, long millis, java.time.LocalDateTime ldt, java.time.ZonedDateTime zdt0, java.util.GregorianCalendar cal) throws Throwable {
        if (cal.getTimeInMillis() != zdt0.toInstant().toEpochMilli() || !cal.equals(GregorianCalendar.from(zdt0))) {
        }
    }
}

