import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Date;

public class TplClass5402 {

    private static final void method(java.lang.String pattern, java.lang.String src, int errorCount) throws Throwable {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.US);
        sdf.setLenient(false);
        ParsePosition pos = new ParsePosition(0);
        Date date = sdf.parse(src, pos);
        if (date != null || pos.getErrorIndex() == -1) {
            errorCount++;
        } else {
        }
    }
}

