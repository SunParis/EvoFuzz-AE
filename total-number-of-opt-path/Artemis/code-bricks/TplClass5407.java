import java.text.SimpleDateFormat;
import java.util.Date;

public class TplClass5407 {

    private static final void method(java.text.SimpleDateFormat fmtFormat, java.lang.String text, java.lang.String EXPECTED, java.text.SimpleDateFormat parseFormat) throws Throwable {
        Date date = parseFormat.parse(text);
        String got = fmtFormat.format(date);
        if (!EXPECTED.equals(got)) {
        }
    }
}

