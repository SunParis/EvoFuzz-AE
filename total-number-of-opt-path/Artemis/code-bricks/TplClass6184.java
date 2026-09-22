import java.util.SortedSet;
import java.util.TreeSet;

public class TplClass6184 {

    private static final void method() throws Throwable {
        try {
            SortedSet m = new TreeSet();
            m.tailSet(null);
        } catch (NullPointerException e) {
        }
    }
}

