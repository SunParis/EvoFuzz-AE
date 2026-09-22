import java.util.Map.Entry;

public class TplClass4063 {

    private static final void method(java.lang.Thread heapDaemon, java.util.Map.Entry<java.lang.Thread, java.lang.StackTraceElement[]> pair) throws Throwable {
        Thread thread = pair.getKey();
        // obtaining stack traces. See b/28261069.
        if (thread == heapDaemon) {
        }
    }
}

