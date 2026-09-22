import java.util.WeakHashMap;

public class TplClass6251 {

    private static final void method(boolean testSucceeded) throws Throwable {
        try {
            // this should generate an IllegalArgumentException
            WeakHashMap bad1 = new WeakHashMap(100, Float.NaN);
        } catch (IllegalArgumentException e1) {
            testSucceeded = true;
        }
    }
}

