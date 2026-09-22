import java.util.SortedSet;
import java.util.TreeSet;

public class TplClass6183 {

    private static final void method() throws Throwable {
        try {
            SortedSet m = new TreeSet();
            m.headSet(null);
        } catch (NullPointerException e) {
        }
    }
}

