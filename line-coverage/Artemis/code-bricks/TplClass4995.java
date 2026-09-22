import java.util.WeakHashMap;
import java.util.Vector;
import java.util.Map;

public class TplClass4995 {

    private static final void method() throws Throwable {
        Map m = new WeakHashMap(100000);
        for (int i = 0; i < 100000; i++) m.put(new Object(), Boolean.TRUE);
        new Vector().addAll(m.keySet());
    }
}

