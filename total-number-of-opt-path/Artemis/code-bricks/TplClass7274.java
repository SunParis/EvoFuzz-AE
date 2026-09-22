import java.text.ParsePosition;
import java.text.ChoiceFormat;

public class TplClass7274 {

    private static final void method(java.lang.StringBuilder after, java.text.ChoiceFormat form, java.text.ParsePosition status) throws Throwable {
        for (double i = 1.0; i <= 7.0; ++i) {
            status.setIndex(0);
            String s = form.format(i);
            after.append(" ");
            after.append(s);
            after.append(form.parse(form.format(i), status));
        }
    }
}

