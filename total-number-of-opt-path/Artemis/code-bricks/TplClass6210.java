import java.util.NoSuchElementException;
import java.util.SortedMap;

public class TplClass6210 {

    private static final void method(boolean exc, java.util.SortedMap m2) throws Throwable {
        try {
            m2.firstKey();
        } catch (NoSuchElementException e) {
            exc = true;
        }
    }
}

