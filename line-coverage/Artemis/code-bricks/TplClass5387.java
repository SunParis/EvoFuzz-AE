import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.text.ParsePosition;

public class TplClass5387 {

    private static final void method() throws Throwable {
        String date = "13 Jan 2005 21:45:34 ABC";
        String format = "dd MMM yyyy HH:mm:ss z";
        ParsePosition pp = new ParsePosition(0);
        pp.setIndex(0);
        SimpleDateFormat sd = new SimpleDateFormat(format, Locale.ENGLISH);
        Date d = sd.parse(date, pp);
        int errorIndex = pp.getErrorIndex();
        if (errorIndex == 21) {
        } else {
        }
    }
}

