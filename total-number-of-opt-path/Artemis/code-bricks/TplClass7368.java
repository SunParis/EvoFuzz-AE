import java.util.Date;
import java.time.LocalDateTime;
import java.time.Instant;

public class TplClass7368 {

    private static final void method(java.util.Date jud, java.time.Instant inst0, int nanos, long millis, java.time.LocalDateTime ldt) throws Throwable {
        if (jud.getTime() != inst0.toEpochMilli() || !jud.equals(Date.from(inst0))) {
        }
    }
}

