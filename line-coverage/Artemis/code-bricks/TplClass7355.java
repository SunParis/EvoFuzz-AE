import java.util.Locale;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.TimeZone;

public class TplClass7355 {

    private static final void method(boolean err, java.util.TimeZone savedTimeZone, java.lang.String golden_data1, java.lang.String golden_data3, java.lang.String golden_data2, java.util.Locale savedLocale) throws Throwable {
        try {
            Locale.setDefault(Locale.US);
            TimeZone.setDefault(TimeZone.getTimeZone("US/Pacific"));
            String test_roll = "";
            GregorianCalendar c_roll = new GregorianCalendar(2001, Calendar.OCTOBER, 27);
            for (int i = 0; i < 7; i++) {
                test_roll += c_roll.get(c_roll.DAY_OF_MONTH) + "-";
                c_roll.roll(c_roll.DAY_OF_YEAR, true);
                test_roll += c_roll.get(c_roll.DAY_OF_MONTH) + " ";
            }
            if (!test_roll.equals(golden_data1)) {
                err = true;
            }
            test_roll = "";
            c_roll = new GregorianCalendar(2001, Calendar.OCTOBER, 27);
            c_roll.setFirstDayOfWeek(Calendar.THURSDAY);
            for (int i = 0; i < 7; i++) {
                test_roll += c_roll.get(c_roll.DAY_OF_MONTH) + "-";
                c_roll.roll(c_roll.DAY_OF_WEEK, true);
                test_roll += c_roll.get(c_roll.DAY_OF_MONTH) + " ";
            }
            if (!test_roll.equals(golden_data2)) {
                err = true;
            }
            test_roll = "";
            c_roll = new GregorianCalendar(2001, Calendar.OCTOBER, 1);
            for (int i = 0; i < 7; i++) {
                test_roll += c_roll.get(c_roll.DAY_OF_MONTH) + "-";
                c_roll.roll(c_roll.DAY_OF_WEEK_IN_MONTH, true);
                test_roll += c_roll.get(c_roll.DAY_OF_MONTH) + " ";
            }
            if (!test_roll.equals(golden_data3)) {
                err = true;
            }
        } finally {
            Locale.setDefault(savedLocale);
            TimeZone.setDefault(savedTimeZone);
        }
    }
}

