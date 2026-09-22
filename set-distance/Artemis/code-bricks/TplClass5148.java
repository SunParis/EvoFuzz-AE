import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Map;

public class TplClass5148 {

    private static final void method() throws Throwable {
        final IdentityHashMap<String, String> identityHashMap = new IdentityHashMap<>();
        identityHashMap.put("One", "Un");
        identityHashMap.put("Two", "Deux");
        identityHashMap.put("Three", "Trois");
        Iterator<Map.Entry<String, String>> entrySetIterator = identityHashMap.entrySet().iterator();
        Map.Entry<String, String> entry = entrySetIterator.next();
        entrySetIterator.remove();
        try {
            entry.getKey();
        } catch (Exception e) {
        }
    }
}

