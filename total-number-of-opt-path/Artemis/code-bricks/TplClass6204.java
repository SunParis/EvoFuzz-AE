import java.util.TreeMap;

public class TplClass6204 {

    private static final void method(java.util.TreeMap<java.lang.String, java.lang.Object> m2) throws Throwable {
        // iterate over the clone (m2) and we should get "one" and "three"
        for (final String key : m2.keySet()) {
            if (!"one".equals(key) && !"three".equals(key)) {
            }
        }
    }
}

