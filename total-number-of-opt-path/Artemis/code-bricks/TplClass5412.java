import java.util.Locale;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParsePosition;

public class TplClass5412 {

    private static final void method() throws Throwable {
        SimpleDateFormat f = new SimpleDateFormat("a", Locale.US);
        Date d1 = f.parse("AM", new ParsePosition(0));
        if (d1.getHours() != 0) {
        }
        Date d2 = f.parse("PM", new ParsePosition(0));
        if (d2.getHours() != 12) {
        }
    }
}

