import java.util.TreeMap;

public class TplClass6205 {

    private static final void method(java.util.TreeMap<java.lang.String, java.lang.Object> m1) throws Throwable {
        // iterate over the original (m1) and we should get "one" and "two"
        for (final String key : m1.keySet()) {
            if (!"one".equals(key) && !"two".equals(key)) {
            }
        }
    }
}

