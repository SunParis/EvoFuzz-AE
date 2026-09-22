import java.util.TreeMap;
import java.util.SortedMap;

public class TplClass6173 {

    private static final void method() throws Throwable {
        try {
            SortedMap m = new TreeMap(String.CASE_INSENSITIVE_ORDER);
            m.headMap(new Integer(0));
        } catch (ClassCastException e) {
        }
    }
}

