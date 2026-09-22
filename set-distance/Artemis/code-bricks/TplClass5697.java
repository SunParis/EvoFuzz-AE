import java.text.MessageFormat;

public class TplClass5697 {

    private static final void method(java.lang.String pattern, java.text.MessageFormat mf) throws Throwable {
        try {
            mf = new MessageFormat(pattern);
        } catch (IllegalArgumentException e) {
            // bad pattern data
        }
    }
}

