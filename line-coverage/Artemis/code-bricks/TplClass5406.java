import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.Date;

public class TplClass5406 {

    private static final void method(java.lang.String[] DATA, java.lang.String EXPECTED) throws Throwable {
        SimpleDateFormat parseFormat = new SimpleDateFormat("MM/dd/yy", Locale.US);
        Calendar cal = new GregorianCalendar(2012 - 80, Calendar.JANUARY, 1);
        parseFormat.set2DigitYearStart(cal.getTime());
        SimpleDateFormat fmtFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
        for (String text : DATA) {
            Date date = parseFormat.parse(text);
            String got = fmtFormat.format(date);
            if (!EXPECTED.equals(got)) {
            }
        }
    }
}

