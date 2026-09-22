import java.util.List;
import java.util.ListIterator;

public class TplClass6942 {

    private static final void method(java.util.List copy) throws Throwable {
        ListIterator i = copy.listIterator();
        copy.remove(99);
        copy.add(new Integer(99));
        i.add(new Integer(666));
    }
}

