import java.util.GregorianCalendar;

public class TplClass7351 {

    private static final void method(java.lang.String test_roll, java.util.GregorianCalendar c_roll) throws Throwable {
        for (int i = 0; i < 7; i++) {
            test_roll += c_roll.get(c_roll.DAY_OF_MONTH) + "-";
            c_roll.roll(c_roll.DAY_OF_WEEK_IN_MONTH, true);
            test_roll += c_roll.get(c_roll.DAY_OF_MONTH) + " ";
        }
    }
}

