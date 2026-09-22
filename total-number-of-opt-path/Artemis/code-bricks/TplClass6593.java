import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.HashMap;

public class TplClass6593 {

    private static final void method() throws Throwable {
        HashMap map = new HashMap();
        Iterator iter = iter = map.entrySet().iterator();
        map.put("key", "value");
        try {
            iter.next();
        } catch (ConcurrentModificationException e) {
        }
    }
}

