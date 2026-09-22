import java.util.LinkedList;
import java.util.ListIterator;

public class TplClass7796 {

    private static final void method() throws Throwable {
        LinkedList list = new LinkedList();
        ListIterator e = list.listIterator();
        Object o = new Integer(1);
        e.add(o);
        e.previous();
        e.next();
        e.remove();
        e.add(o);
        if (!o.equals(list.get(0)))
            ;
    }
}

