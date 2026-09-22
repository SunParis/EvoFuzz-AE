import java.util.SortedSet;
import java.util.TreeSet;

public class TplClass6177 {

    private static final void method() throws Throwable {
        try {
            SortedSet m = new TreeSet(String.CASE_INSENSITIVE_ORDER);
            m.headSet(new Integer(0));
        } catch (ClassCastException e) {
        }
    }
}

