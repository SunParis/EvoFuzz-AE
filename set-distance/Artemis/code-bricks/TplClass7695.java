import java.util.Collections;
import java.util.Set;

public class TplClass7695 {

    private static final void method(boolean testSucceeded) throws Throwable {
        try {
            Set c = Collections.unmodifiableSet(null);
        } catch (NullPointerException e) {
            testSucceeded = true;
        }
    }
}

