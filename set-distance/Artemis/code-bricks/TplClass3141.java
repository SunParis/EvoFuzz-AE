import java.util.Map.Entry;

public class TplClass3141 {

    private static final void method(java.util.Map.Entry<java.lang.Thread, java.lang.StackTraceElement[]> entry, java.lang.Thread.State state) throws Throwable {
        if (state != Thread.State.RUNNABLE && state != Thread.State.TIMED_WAITING) {
            for (StackTraceElement e : entry.getValue()) {
            }
        }
    }
}

