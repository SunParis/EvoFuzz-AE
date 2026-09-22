import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class TplClass6592 {

    private static final void method(java.util.Iterator iter) throws Throwable {
        try {
            iter.next();
        } catch (ConcurrentModificationException e) {
        }
    }
}

