import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.Locale;

public class TplClass7342 {

    private static final void method() throws Throwable {
        Calendar cal = new GregorianCalendar(Locale.US);
        cal.setLenient(false);
        cal.set(Calendar.YEAR, 2007);
        cal.set(Calendar.MONTH, Calendar.NOVEMBER);
        cal.set(Calendar.WEEK_OF_MONTH, 4);
        cal.set(Calendar.DAY_OF_WEEK, 1);
        // Let cal calculate the time from the given fields
        cal.getTime();
        // Change DAY_OF_MONTH
        cal.set(Calendar.DAY_OF_MONTH, 1);
        // The following line shouldn't throw an IllegalArgumentException.
        cal.getTime();
    }
}

