import java.util.Date;
import java.time.LocalDateTime;
import java.time.Instant;

public class TplClass7369 {

    private static final void method(java.time.Instant inst_ms, int nanos, long millis, java.time.LocalDateTime ldt, java.util.Date jud0) throws Throwable {
        if (jud0.getTime() != inst_ms.toEpochMilli() || !inst_ms.equals(jud0.toInstant())) {
        }
    }
}

