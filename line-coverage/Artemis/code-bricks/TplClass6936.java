import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.ListIterator;

public class TplClass6936 {

    private static final void method(java.util.List copy) throws Throwable {
        try {
            ListIterator i = copy.listIterator();
            i.next();
            copy.remove(99);
            copy.add(new Integer(99));
            i.remove();
        } catch (ConcurrentModificationException e) {
        }
    }
}

