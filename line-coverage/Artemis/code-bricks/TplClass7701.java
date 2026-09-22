import java.util.Collections;
import java.util.Set;

public class TplClass7701 {

    private static final void method(boolean testSucceeded) throws Throwable {
        try {
            Set c = Collections.synchronizedSet(null);
        } catch (NullPointerException e) {
            testSucceeded = true;
        }
    }
}

