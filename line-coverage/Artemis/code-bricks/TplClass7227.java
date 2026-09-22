import java.util.Set;
import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map.Entry;
import java.util.Map;

public class TplClass7227 {

    private static final void method() throws Throwable {
        final ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put("One", "Un");
        concurrentHashMap.put("Two", "Deux");
        concurrentHashMap.put("Three", "Trois");
        Set<Map.Entry<String, String>> entrySet = concurrentHashMap.entrySet();
        HashSet<Map.Entry<String, String>> hashSet = new HashSet<>(entrySet);
        if (false == hashSet.equals(entrySet)) {
        }
        if (hashSet.hashCode() != entrySet.hashCode()) {
        }
    }
}

