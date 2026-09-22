import java.util.SortedMap;
import java.util.Collections;

public class TplClass7705 {

    private static final void method(boolean testSucceeded) throws Throwable {
        try {
            SortedMap c = Collections.synchronizedSortedMap(null);
        } catch (NullPointerException e) {
            testSucceeded = true;
        }
    }
}

