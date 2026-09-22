import java.util.List;
import java.util.Collections;

public class TplClass7702 {

    private static final void method(boolean testSucceeded) throws Throwable {
        try {
            List c = Collections.synchronizedList(null);
        } catch (NullPointerException e) {
            testSucceeded = true;
        }
    }
}

