import java.util.Map;
import java.util.Collections;

public class TplClass7697 {

    private static final void method(boolean testSucceeded) throws Throwable {
        try {
            Map c = Collections.unmodifiableMap(null);
        } catch (NullPointerException e) {
            testSucceeded = true;
        }
    }
}

