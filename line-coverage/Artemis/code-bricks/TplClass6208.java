import java.util.TreeMap;

public class TplClass6208 {

    private static final void method() throws Throwable {
        TreeMap<String, Object> m1 = new TreeMap<String, Object>();
        m1.put("one", 1);
        m1.keySet();
        TreeMap<String, Object> m2 = (TreeMap<String, Object>) m1.clone();
        m1.put("two", 2);
        m2.put("three", 3);
        // iterate over the clone (m2) and we should get "one" and "three"
        for (final String key : m2.keySet()) {
            if (!"one".equals(key) && !"three".equals(key)) {
            }
        }
        // iterate over the original (m1) and we should get "one" and "two"
        for (final String key : m1.keySet()) {
            if (!"one".equals(key) && !"two".equals(key)) {
            }
        }
    }
}

