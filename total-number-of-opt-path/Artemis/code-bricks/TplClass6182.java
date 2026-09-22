import java.util.TreeMap;
import java.util.SortedMap;

public class TplClass6182 {

    private static final void method() throws Throwable {
        try {
            SortedMap m = new TreeMap(String.CASE_INSENSITIVE_ORDER);
            m.tailMap(null);
        } catch (NullPointerException e) {
        }
    }
}

