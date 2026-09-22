import java.util.HashMap;

public class TplClass6247 {

    private static final void method(boolean testSucceeded) throws Throwable {
        try {
            // this should generate an IllegalArgumentException
            HashMap bad1 = new HashMap(100, Float.NaN);
        } catch (IllegalArgumentException e1) {
            testSucceeded = true;
        }
    }
}

