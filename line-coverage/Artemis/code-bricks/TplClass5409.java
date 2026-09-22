import java.util.Locale;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class TplClass5409 {

    private static final void method() throws Throwable {
        SimpleDateFormat sdf = new SimpleDateFormat("zzzz", Locale.US);
        sdf.setTimeZone(TimeZone.getTimeZone("Australia/Lord_Howe"));
        String got = sdf.format(new Date());
        if (!got.equals("Lord Howe Standard Time") && !got.equals("Lord Howe Daylight Time")) {
        }
    }
}

