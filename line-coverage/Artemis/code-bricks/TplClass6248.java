import java.util.HashSet;

public class TplClass6248 {

    private static final void method(boolean testSucceeded) throws Throwable {
        try {
            // this should generate an IllegalArgumentException
            HashSet bad1 = new HashSet(100, -3);
        } catch (IllegalArgumentException e1) {
            testSucceeded = true;
        }
    }
}

