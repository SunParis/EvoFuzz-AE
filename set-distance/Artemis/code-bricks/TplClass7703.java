import java.util.Map;
import java.util.Collections;

public class TplClass7703 {

    private static final void method(boolean testSucceeded) throws Throwable {
        try {
            Map c = Collections.synchronizedMap(null);
        } catch (NullPointerException e) {
            testSucceeded = true;
        }
    }
}

