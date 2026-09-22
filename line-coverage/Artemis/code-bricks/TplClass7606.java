import java.util.Deque;

public class TplClass7606 {

    private static final void method(java.lang.Iterable<java.util.Deque<java.lang.Integer>> deqs, java.util.Deque<java.util.Iterator<java.lang.Integer>> dits) throws Throwable {
        for (Deque<Integer> deq : deqs) dits.addLast(deq.descendingIterator());
    }
}

