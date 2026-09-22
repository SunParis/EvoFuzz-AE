import java.util.Map;
import java.util.Map.Entry;
import java.util.HashMap;

public class TplClass7591 {

    private static final void method(java.lang.String newValue, java.lang.String oldValue, java.lang.String key) throws Throwable {
        Map m = new HashMap();
        m.put(key, oldValue);
        Map.Entry e = (Map.Entry) m.entrySet().iterator().next();
        Object returnVal = e.setValue(newValue);
        if (!returnVal.equals(oldValue))
            ;
    }
}

