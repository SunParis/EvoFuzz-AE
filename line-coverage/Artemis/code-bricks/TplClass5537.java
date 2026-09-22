import java.util.UUID;

public class TplClass5537 {

    private static final void method(java.util.UUID test) throws Throwable {
        try {
            test.timestamp();
        } catch (UnsupportedOperationException uoe) {
            // Correct result
        }
    }
}

