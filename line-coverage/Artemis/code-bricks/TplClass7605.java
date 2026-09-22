import java.util.Deque;

public class TplClass7605 {

    private static final void method(java.util.Deque<java.util.Iterator<java.lang.Integer>> its, java.lang.Iterable<java.util.Deque<java.lang.Integer>> deqs) throws Throwable {
        for (Deque<Integer> deq : deqs) its.addLast(deq.iterator());
    }
}

