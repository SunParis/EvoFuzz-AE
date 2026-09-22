import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.ArrayList;
import java.util.ListIterator;

public class TplClass6939 {

    private static final void method() throws Throwable {
        List orig = new ArrayList(100);
        for (int i = 0; i < 100; i++) orig.add(new Integer(i));
        List copy = new ArrayList(orig);
        try {
            ListIterator i = copy.listIterator();
            i.next();
            copy.remove(99);
            copy.add(new Integer(99));
            i.remove();
        } catch (ConcurrentModificationException e) {
        }
        if (!copy.equals(orig))
            ;
        try {
            ListIterator i = copy.listIterator();
            i.next();
            copy.remove(99);
            copy.add(new Integer(99));
            i.set(new Integer(666));
        } catch (ConcurrentModificationException e) {
        }
        if (!copy.equals(orig))
            ;
        try {
            ListIterator i = copy.listIterator();
            copy.remove(99);
            copy.add(new Integer(99));
            i.add(new Integer(666));
        } catch (ConcurrentModificationException e) {
        }
        if (!copy.equals(orig))
            ;
    }
}

