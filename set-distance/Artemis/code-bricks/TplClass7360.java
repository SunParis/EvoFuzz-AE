import java.util.GregorianCalendar;

public class TplClass7360 {

    private static final void method(java.lang.String test_roll, java.util.GregorianCalendar c_roll) throws Throwable {
        test_roll += c_roll.get(c_roll.DAY_OF_MONTH) + "-";
        c_roll.roll(c_roll.DAY_OF_WEEK, true);
        test_roll += c_roll.get(c_roll.DAY_OF_MONTH) + " ";
    }
}

