import java.util.List;
import java.util.Collections;

public class TplClass7696 {

    private static final void method(boolean testSucceeded) throws Throwable {
        try {
            List c = Collections.unmodifiableList(null);
        } catch (NullPointerException e) {
            testSucceeded = true;
        }
    }
}

