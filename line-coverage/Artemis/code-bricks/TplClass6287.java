import java.util.MissingResourceException;

public class TplClass6287 {

    private static final void method(int count, java.lang.Throwable cause) throws Throwable {
        while ((cause = cause.getCause()) != null) {
            if (cause instanceof MissingResourceException) {
                count++;
            }
        }
    }
}

