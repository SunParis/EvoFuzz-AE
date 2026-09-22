import java.util.Map;
import java.util.Map.Entry;

public class TplClass4058 {

    private static final void method(java.lang.Thread heapDaemon, java.util.Map<java.lang.Thread, java.lang.StackTraceElement[]> map) throws Throwable {
        for (Map.Entry<Thread, StackTraceElement[]> pair : map.entrySet()) {
            Thread thread = pair.getKey();
            // Expect empty stack trace since we do not support suspending the GC thread for
            // obtaining stack traces. See b/28261069.
            if (thread == heapDaemon) {
            }
        }
    }
}

