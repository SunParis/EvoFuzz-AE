import java.util.Collections;
import java.util.Collection;

public class TplClass7700 {

    private static final void method(boolean testSucceeded) throws Throwable {
        try {
            Collection c = Collections.synchronizedCollection(null);
        } catch (NullPointerException e) {
            testSucceeded = true;
        }
    }
}

