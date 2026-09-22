import java.util.WeakHashMap;
import java.util.ArrayList;
import java.util.Map;

public class TplClass4986 {

    private static final void method() throws Throwable {
        for (int j = 0; j < 1; j++) {
            Map m = new WeakHashMap(100000);
            for (int i = 0; i < 100000; i++) m.put(new Object(), Boolean.TRUE);
            new ArrayList().addAll(m.keySet());
        }
    }
}

