import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

public class TplClass6343 {

    private static final void method(java.util.Map<java.lang.Object, java.lang.Object> map, java.lang.Exception failure) throws Throwable {
        try {
            for (Map.Entry<Object, Object> e : map.entrySet()) {
                Object key = e.getKey();
                Object value = e.getValue();
                int expectedEntryHashCode = (Objects.hashCode(key) ^ Objects.hashCode(value));
                if (e.hashCode() != expectedEntryHashCode) {
                }
            }
        } catch (Exception e) {
            if (failure == null) {
                failure = e;
            } else {
                failure.addSuppressed(e);
            }
        } finally {
            map.clear();
        }
    }
}

