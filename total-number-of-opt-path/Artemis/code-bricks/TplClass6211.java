import java.util.NoSuchElementException;
import java.util.SortedMap;

public class TplClass6211 {

    private static final void method(boolean exc, java.util.SortedMap m2) throws Throwable {
        try {
            m2.lastKey();
        } catch (NoSuchElementException e) {
            exc = true;
        }
    }
}

