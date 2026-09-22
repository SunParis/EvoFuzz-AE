import java.util.WeakHashMap;

public class TplClass6260 {

    private static final void method() throws Throwable {
        // this should generate an IllegalArgumentException
        WeakHashMap bad1 = new WeakHashMap(100, -3);
    }
}

