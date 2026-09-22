import java.util.TreeMap;
import java.util.SortedMap;

public class TplClass6180 {

    private static final void method() throws Throwable {
        try {
            SortedMap m = new TreeMap();
            m.tailMap(null);
        } catch (NullPointerException e) {
        }
    }
}

