import java.util.SortedSet;
import java.util.TreeSet;

public class TplClass6185 {

    private static final void method() throws Throwable {
        try {
            SortedSet m = new TreeSet(String.CASE_INSENSITIVE_ORDER);
            m.headSet(null);
        } catch (NullPointerException e) {
        }
    }
}

