import java.util.Collections;
import java.util.Collection;

public class TplClass7694 {

    private static final void method(boolean testSucceeded) throws Throwable {
        try {
            Collection c = Collections.unmodifiableCollection(null);
        } catch (NullPointerException e) {
            testSucceeded = true;
        }
    }
}

