import java.util.Iterator;
import java.util.Deque;

public class TplClass7607 {

    private static final void method(java.util.Deque<java.lang.Integer> deq) throws Throwable {
        while (deq.size() > 1) {
            Iterator<Integer> it = deq.iterator();
            it.next();
            it.remove();
            it = deq.descendingIterator();
            it.next();
            it.remove();
        }
    }
}

