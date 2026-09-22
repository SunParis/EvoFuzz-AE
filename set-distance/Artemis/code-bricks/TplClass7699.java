import java.util.SortedMap;
import java.util.Collections;

public class TplClass7699 {

    private static final void method(boolean testSucceeded) throws Throwable {
        try {
            SortedMap c = Collections.unmodifiableSortedMap(null);
        } catch (NullPointerException e) {
            testSucceeded = true;
        }
    }
}

