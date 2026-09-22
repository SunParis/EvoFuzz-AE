import java.util.HashMap;

public class TplClass7580 {

    private static final void method(java.util.HashMap<java.lang.Integer, java.lang.Integer> hm, int ENTRIES) throws Throwable {
        // Add items to one more than the resize threshold
        for (int i = 0; i < ENTRIES; i++) {
            hm.put(i * 10, i * 10);
        }
    }
}

