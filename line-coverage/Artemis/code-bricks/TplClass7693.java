import java.util.List;
import java.util.Arrays;

public class TplClass7693 {

    private static final void method(boolean testSucceeded) throws Throwable {
        try {
            List l = Arrays.asList(null);
        } catch (NullPointerException e) {
            testSucceeded = true;
        }
    }
}

