import java.util.Map.Entry;

public class TplClass3144 {

    private static final void method(java.util.Map.Entry<java.lang.Thread, java.lang.StackTraceElement[]> entry, boolean found, java.lang.Thread t) throws Throwable {
        Thread.State state = t.getState();
        if (state != Thread.State.RUNNABLE && state != Thread.State.TIMED_WAITING) {
            for (StackTraceElement e : entry.getValue()) {
            }
        }
        found = true;
    }
}

