import java.util.Vector;

public class TplClass5164 {

    private static final void method(int testSucceeded) throws Throwable {
        try {
            // this should generate an IllegalArgumentException
            Vector bad1 = new Vector(-100, 10);
        } catch (IllegalArgumentException e1) {
            testSucceeded = 1;
        } catch (NegativeArraySizeException e2) {
            testSucceeded = 0;
        }
    }
}

