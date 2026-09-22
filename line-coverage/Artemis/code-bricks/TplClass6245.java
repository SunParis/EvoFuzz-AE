import java.util.Hashtable;

public class TplClass6245 {

    private static final void method(boolean testSucceeded) throws Throwable {
        try {
            // this should generate an IllegalArgumentException
            Hashtable bad1 = new Hashtable(100, Float.NaN);
        } catch (IllegalArgumentException e1) {
            testSucceeded = true;
        }
    }
}

