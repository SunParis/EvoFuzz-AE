import java.text.SimpleDateFormat;
import java.util.Date;

public class TplClass5404 {

    private static final void method(java.text.SimpleDateFormat fmtFormat, java.lang.String[] DATA, java.lang.String EXPECTED, java.text.SimpleDateFormat parseFormat) throws Throwable {
        for (String text : DATA) {
            Date date = parseFormat.parse(text);
            String got = fmtFormat.format(date);
            if (!EXPECTED.equals(got)) {
            }
        }
    }
}

