import java.util.Iterator;
import java.util.Set;

public class TplClass7500 {

    private static final void method(java.util.Iterator e, java.util.Set intersection) throws Throwable {
        while (e.hasNext()) if (!intersection.remove(e.next()))
            ;
    }
}

