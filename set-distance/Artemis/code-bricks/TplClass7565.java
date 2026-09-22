import java.util.Set;
import java.util.Map;

public class TplClass7565 {

    private static final void method(java.util.Map<java.lang.Integer, java.util.Set<java.lang.String>> tree, java.util.Set<java.lang.String> ids) throws Throwable {
        for (Integer key : tree.keySet()) {
            Set<String> s1 = tree.get(key);
            ids.removeAll(s1);
        }
    }
}

