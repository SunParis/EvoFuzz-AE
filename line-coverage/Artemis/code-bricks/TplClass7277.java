import java.text.ParsePosition;
import java.text.ChoiceFormat;

public class TplClass7277 {

    private static final void method() throws Throwable {
        boolean err = false;
        // Borrow an example in API doc
        double[] limits = { 1, 2, 3, 4, 5, 6, 7 };
        String[] dayOfWeekNames = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
        ChoiceFormat form = new ChoiceFormat(limits, dayOfWeekNames);
        ParsePosition status = new ParsePosition(0);
        StringBuilder before = new StringBuilder();
        for (double i = 1.0; i <= 7.0; ++i) {
            status.setIndex(0);
            String s = form.format(i);
            before.append(" ");
            before.append(s);
            before.append(form.parse(form.format(i), status));
        }
        String original = before.toString();
        double[] newLimits = form.getLimits();
        String[] newFormats = (String[]) form.getFormats();
        newFormats[6] = "Doyoubi";
        StringBuilder after = new StringBuilder();
        for (double i = 1.0; i <= 7.0; ++i) {
            status.setIndex(0);
            String s = form.format(i);
            after.append(" ");
            after.append(s);
            after.append(form.parse(form.format(i), status));
        }
        if (!original.equals(after.toString())) {
            err = true;
        }
        dayOfWeekNames[6] = "Saturday";
        after = new StringBuilder();
        for (double i = 1.0; i <= 7.0; ++i) {
            status.setIndex(0);
            String s = form.format(i);
            after.append(" ");
            after.append(s);
            after.append(form.parse(form.format(i), status));
        }
        if (!original.equals(after.toString())) {
            err = true;
        }
        if (err) {
        } else {
        }
    }
}

