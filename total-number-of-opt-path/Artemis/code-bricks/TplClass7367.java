import java.util.GregorianCalendar;
import java.util.Date;
import java.time.LocalDateTime;
import java.util.Random;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.TimeZone;

public class TplClass7367 {

    private static final void method(java.util.Random r, int NANOS_PER_SECOND, long t1970, int N) throws Throwable {
        for (int i = 0; i < N; i++) {
            int days = r.nextInt(50) * 365 + r.nextInt(365);
            long secs = t1970 + days * 86400 + r.nextInt(86400);
            int nanos = r.nextInt(NANOS_PER_SECOND);
            // millis precision
            int nanos_ms = nanos / 1000000 * 1000000;
            long millis = secs * 1000 + r.nextInt(1000);
            LocalDateTime ldt = LocalDateTime.ofEpochSecond(secs, nanos, ZoneOffset.UTC);
            LocalDateTime ldt_ms = LocalDateTime.ofEpochSecond(secs, nanos_ms, ZoneOffset.UTC);
            Instant inst = Instant.ofEpochSecond(secs, nanos);
            Instant inst_ms = Instant.ofEpochSecond(secs, nanos_ms);
            // /////////// java.util.Date /////////////////////////
            Date jud = new java.util.Date(millis);
            Instant inst0 = jud.toInstant();
            if (jud.getTime() != inst0.toEpochMilli() || !jud.equals(Date.from(inst0))) {
            }
            // roundtrip only with millis precision
            Date jud0 = Date.from(inst_ms);
            if (jud0.getTime() != inst_ms.toEpochMilli() || !inst_ms.equals(jud0.toInstant())) {
            }
            // ////////// java.util.GregorianCalendar /////////////
            GregorianCalendar cal = new GregorianCalendar();
            // non-roundtrip of tz name between j.u.tz and j.t.zid
            cal.setTimeZone(TimeZone.getTimeZone(ZoneId.systemDefault()));
            cal.setGregorianChange(new java.util.Date(Long.MIN_VALUE));
            cal.setFirstDayOfWeek(Calendar.MONDAY);
            cal.setMinimalDaysInFirstWeek(4);
            cal.setTimeInMillis(millis);
            ZonedDateTime zdt0 = cal.toZonedDateTime();
            if (cal.getTimeInMillis() != zdt0.toInstant().toEpochMilli() || !cal.equals(GregorianCalendar.from(zdt0))) {
            }
            inst0 = cal.toInstant();
            if (cal.getTimeInMillis() != inst0.toEpochMilli()) {
            }
            ZonedDateTime zdt = ZonedDateTime.of(ldt_ms, ZoneId.systemDefault());
            GregorianCalendar cal0 = GregorianCalendar.from(zdt);
            if (zdt.toInstant().toEpochMilli() != cal0.getTimeInMillis() || !zdt.equals(GregorianCalendar.from(zdt).toZonedDateTime())) {
            }
        }
    }
}

