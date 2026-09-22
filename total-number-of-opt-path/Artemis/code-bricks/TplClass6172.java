import java.util.TreeMap;
import java.util.SortedMap;

public class TplClass6172 {

    private static final void method() throws Throwable {
        try {
            SortedMap m = new TreeMap();
            m.tailMap(new Object());
        } catch (ClassCastException e) {
        }
    }
}

