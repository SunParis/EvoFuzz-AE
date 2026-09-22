import java.util.Map;

public class TplClass4064 {

    private static final void method() throws Throwable {
        if (Thread.currentThread().getId() <= 0) {
        }
        // Check all the current threads for positive IDs.
        Map<Thread, StackTraceElement[]> stMap = Thread.getAllStackTraces();
        for (Thread thread : stMap.keySet()) {
            if (thread.getId() <= 0) {
            }
        }
    }
}

