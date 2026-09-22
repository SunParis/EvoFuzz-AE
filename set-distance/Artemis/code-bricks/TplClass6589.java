import java.util.Map;

public class TplClass6589 {

    private static final void method(java.util.Map m, int NUM_KEYS) throws Throwable {
        for (int i = 0; i < NUM_KEYS; i++) {
            m.put(new Integer(i), "");
            int eldest = ((Integer) m.keySet().iterator().next()).intValue();
            if (eldest != Math.max(i - 9, 0))
                ;
        }
    }
}

