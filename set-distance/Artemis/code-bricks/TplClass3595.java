import java.util.Locale;
import java.util.MissingResourceException;

public class TplClass3595 {

    private static final void method() throws Throwable {
        Locale loc;
        loc = new Locale("en", "US");
        loc = new Locale("eng", "USA");
        try {
        } catch (MissingResourceException mre) {
        }
    }
}

