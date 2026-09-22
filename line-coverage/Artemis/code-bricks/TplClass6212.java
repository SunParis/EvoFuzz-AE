import java.util.SortedSet;
import java.util.NoSuchElementException;

public class TplClass6212 {

    private static final void method(boolean exc, java.util.SortedSet s2) throws Throwable {
        try {
            s2.first();
        } catch (NoSuchElementException e) {
            exc = true;
        }
    }
}

