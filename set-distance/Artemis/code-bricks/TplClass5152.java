import java.util.Map.Entry;
import java.util.Map;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Set;

public class TplClass5152 {

    private static final void method() throws Throwable {
        final IdentityHashMap<String, String> identityHashMap = new IdentityHashMap<>();
        identityHashMap.put("One", "Un");
        identityHashMap.put("Two", "Deux");
        identityHashMap.put("Three", "Trois");
        Set<Map.Entry<String, String>> entrySet = identityHashMap.entrySet();
        HashSet<Map.Entry<String, String>> hashSet = new HashSet<>(entrySet);
        // keys put into 'identityHashMap' above are equal to any other.
        if (false == hashSet.equals(entrySet)) {
        }
        if (hashSet.hashCode() != entrySet.hashCode()) {
        }
    }
}

