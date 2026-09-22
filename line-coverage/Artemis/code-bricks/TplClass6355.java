import java.util.Map;
import java.util.List;
import java.util.Iterator;

public class TplClass6355 {

    private static final void method(java.lang.Object x1, java.lang.Object x2, java.util.List<java.util.Map> maps) throws Throwable {
        for (Map map : maps) {
            Iterator it = map.keySet().iterator();
            while (it.hasNext()) {
                Object x = it.next();
                if (x == x1 || x == x2)
                    it.remove();
            }
        }
    }
}

