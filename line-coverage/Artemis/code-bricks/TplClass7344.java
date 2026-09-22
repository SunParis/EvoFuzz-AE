import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class TplClass7344 {

    private static final void method() throws Throwable {
        GregorianCalendar cal = new GregorianCalendar();
        cal.set(Calendar.YEAR, 1997);
        cal.set(Calendar.DAY_OF_YEAR, 1);
        // Should be Jan 1
        Date d = cal.getTime();
        if (d.getMonth() != 0 || d.getDate() != 1) {
        }
    }
}

