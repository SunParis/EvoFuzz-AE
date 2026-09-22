import java.util.ListIterator;

public class TplClass7684 {

    private static final void method(java.util.ListIterator itAll) throws Throwable {
        while (itAll.hasNext()) {
            Integer i = (Integer) itAll.next();
            itAll.set(new Integer(i.intValue()));
        }
    }
}

