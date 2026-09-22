import java.text.ParsePosition;
import java.text.ChoiceFormat;

public class TplClass7273 {

    private static final void method(java.text.ChoiceFormat form, java.lang.StringBuilder before, java.text.ParsePosition status) throws Throwable {
        for (double i = 1.0; i <= 7.0; ++i) {
            status.setIndex(0);
            String s = form.format(i);
            before.append(" ");
            before.append(s);
            before.append(form.parse(form.format(i), status));
        }
    }
}

