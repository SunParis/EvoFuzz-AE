import java.text.ParsePosition;
import java.text.ChoiceFormat;

public class TplClass7279 {

    private static final void method(double i, java.lang.StringBuilder after, java.text.ChoiceFormat form, java.text.ParsePosition status) throws Throwable {
        status.setIndex(0);
        String s = form.format(i);
        after.append(" ");
        after.append(s);
        after.append(form.parse(form.format(i), status));
    }
}

