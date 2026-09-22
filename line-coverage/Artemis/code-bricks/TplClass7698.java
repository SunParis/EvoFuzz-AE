import java.util.SortedSet;
import java.util.Collections;

public class TplClass7698 {

    private static final void method(boolean testSucceeded) throws Throwable {
        try {
            SortedSet c = Collections.unmodifiableSortedSet(null);
        } catch (NullPointerException e) {
            testSucceeded = true;
        }
    }
}

