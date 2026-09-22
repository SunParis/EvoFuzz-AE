import java.util.HashSet;

public class TplClass6249 {

    private static final void method(boolean testSucceeded) throws Throwable {
        try {
            // this should generate an IllegalArgumentException
            HashSet bad1 = new HashSet(100, Float.NaN);
        } catch (IllegalArgumentException e1) {
            testSucceeded = true;
        }
    }
}

