import java.util.Set;
import java.util.Map;
import java.util.HashSet;

public class TplClass7568 {

    private static final void method(java.util.Map<java.lang.Integer, java.util.Set<java.lang.String>> tree, java.util.Set<java.lang.String> s, java.lang.Integer offset) throws Throwable {
        if (s == null) {
            s = new HashSet<>();
            tree.put(offset, s);
        }
    }
}

