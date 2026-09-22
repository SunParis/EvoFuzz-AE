import java.util.GregorianCalendar;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class TplClass7372 {

    private static final void method(java.time.ZonedDateTime zdt, int nanos, long millis, java.util.GregorianCalendar cal0, java.time.LocalDateTime ldt) throws Throwable {
        if (zdt.toInstant().toEpochMilli() != cal0.getTimeInMillis() || !zdt.equals(GregorianCalendar.from(zdt).toZonedDateTime())) {
        }
    }
}

