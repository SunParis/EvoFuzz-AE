import java.util.MissingResourceException;

public class TplClass6290 {

    private static final void method(java.util.MissingResourceException e) throws Throwable {
        Throwable cause = e;
        int count = 0;
        while ((cause = cause.getCause()) != null) {
            if (cause instanceof MissingResourceException) {
                count++;
            }
        }
        if (count > 0) {
        }
    }
}

