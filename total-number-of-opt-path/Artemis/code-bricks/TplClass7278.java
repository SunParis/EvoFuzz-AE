import java.text.ParsePosition;
import java.text.ChoiceFormat;

public class TplClass7278 {

    private static final void method(double i, java.text.ChoiceFormat form, java.lang.StringBuilder before, java.text.ParsePosition status) throws Throwable {
        status.setIndex(0);
        String s = form.format(i);
        before.append(" ");
        before.append(s);
        before.append(form.parse(form.format(i), status));
    }
}

