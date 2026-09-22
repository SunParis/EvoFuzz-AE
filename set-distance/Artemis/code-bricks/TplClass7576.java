import java.util.Set;
import java.util.Map;

public class TplClass7576 {

    private static final void method(java.util.Map<java.lang.Integer, java.util.Set<java.lang.String>> tree, java.util.Set<java.lang.String> ids, java.lang.Integer key) throws Throwable {
        Set<String> s1 = tree.get(key);
        ids.removeAll(s1);
    }
}

