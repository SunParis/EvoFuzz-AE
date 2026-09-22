import java.util.Iterator;
import java.util.Deque;

public class TplClass7609 {

    private static final void method(java.util.Deque<java.lang.Integer> deq) throws Throwable {
        Iterator<Integer> it = deq.iterator();
        it.next();
        it.remove();
        it = deq.descendingIterator();
        it.next();
        it.remove();
    }
}

