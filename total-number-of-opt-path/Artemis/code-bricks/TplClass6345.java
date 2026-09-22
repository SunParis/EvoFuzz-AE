import java.util.Map.Entry;
import java.util.Objects;

public class TplClass6345 {

    private static final void method(java.util.Map.Entry<java.lang.Object, java.lang.Object> e) throws Throwable {
        Object key = e.getKey();
        Object value = e.getValue();
        int expectedEntryHashCode = (Objects.hashCode(key) ^ Objects.hashCode(value));
        if (e.hashCode() != expectedEntryHashCode) {
        }
    }
}

