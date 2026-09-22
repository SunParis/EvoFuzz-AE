import java.util.TreeMap;
import java.util.SortedMap;

public class TplClass6181 {

    private static final void method() throws Throwable {
        try {
            SortedMap m = new TreeMap(String.CASE_INSENSITIVE_ORDER);
            m.headMap(null);
        } catch (NullPointerException e) {
        }
    }
}

