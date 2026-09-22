import java.util.SortedSet;
import java.util.Collections;

public class TplClass7704 {

    private static final void method(boolean testSucceeded) throws Throwable {
        try {
            SortedSet c = Collections.synchronizedSortedSet(null);
        } catch (NullPointerException e) {
            testSucceeded = true;
        }
    }
}

