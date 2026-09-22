import java.util.Iterator;
import java.util.List;

public class TplClass7554 {

    private static final void method(java.util.List<java.lang.Integer> sorted) throws Throwable {
        for (Iterator<Integer> i = sorted.iterator(); i.hasNext(); ) if ((i.next().intValue() & 1) == 1)
            i.remove();
    }
}

