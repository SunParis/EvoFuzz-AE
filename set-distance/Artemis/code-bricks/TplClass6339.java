import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

public class TplClass6339 {

    private static final void method(java.util.Map<java.lang.Object, java.lang.Object> map) throws Throwable {
        for (Map.Entry<Object, Object> e : map.entrySet()) {
            Object key = e.getKey();
            Object value = e.getValue();
            int expectedEntryHashCode = (Objects.hashCode(key) ^ Objects.hashCode(value));
            if (e.hashCode() != expectedEntryHashCode) {
            }
        }
    }
}

