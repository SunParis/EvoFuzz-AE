import java.util.Iterator;
import java.util.Queue;

public class TplClass7553 {

    private static final void method(java.util.Queue<java.lang.Integer> pq) throws Throwable {
        for (Iterator<Integer> i = pq.iterator(); i.hasNext(); ) if ((i.next().intValue() & 1) == 1)
            i.remove();
    }
}

