import java.util.SortedSet;
import java.util.NoSuchElementException;

public class TplClass6213 {

    private static final void method(boolean exc, java.util.SortedSet s2) throws Throwable {
        try {
            s2.last();
        } catch (NoSuchElementException e) {
            exc = true;
        }
    }
}

