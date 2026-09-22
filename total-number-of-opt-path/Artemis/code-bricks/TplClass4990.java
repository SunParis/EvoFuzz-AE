import java.util.List;
import java.util.WeakHashMap;
import java.util.ArrayList;
import java.util.Map;

public class TplClass4990 {

    private static final void method() throws Throwable {
        for (int j = 0; j < 1; j++) {
            Map m = new WeakHashMap(100000);
            for (int i = 0; i < 100000; i++) m.put(new Object(), Boolean.TRUE);
            List list = new ArrayList();
            list.add("inka");
            list.add("dinka");
            list.add("doo");
            list.addAll(1, m.keySet());
        }
    }
}

