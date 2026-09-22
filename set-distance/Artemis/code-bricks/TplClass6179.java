import java.util.TreeMap;
import java.util.SortedMap;

public class TplClass6179 {

    private static final void method() throws Throwable {
        try {
            SortedMap m = new TreeMap();
            m.headMap(null);
        } catch (NullPointerException e) {
        }
    }
}

