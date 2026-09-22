import java.util.SortedMap;
import java.nio.charset.Charset;
import java.util.Set;
import java.util.Map;
import java.util.Map.Entry;

public class TplClass2569 {

    private static final void method(java.util.SortedMap<java.lang.String, java.nio.charset.Charset> all, java.util.Set<java.lang.String> needed) throws Throwable {
        for (Map.Entry<String, Charset> e : all.entrySet()) {
            String canonicalName = e.getKey();
            needed.remove(canonicalName);
        }
    }
}

