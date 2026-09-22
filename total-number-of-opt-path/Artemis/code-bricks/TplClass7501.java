import java.util.Iterator;
import java.util.Set;

public class TplClass7501 {

    private static final void method(java.util.Iterator e, java.util.Set union) throws Throwable {
        while (e.hasNext()) {
            Object o = e.next();
            if (!union.contains(o))
                ;
            e.remove();
            if (union.contains(o))
                ;
        }
    }
}

