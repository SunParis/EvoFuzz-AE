import java.util.TimerTask;
import java.util.Timer;

public class TplClass5421 {

    private static final void method(java.util.Timer t) throws Throwable {
        // Try to start another event
        try {
            // Timer thread is dead now
            t.schedule(new TimerTask() {

                public void run() {
                }
            }, 0);
        } catch (IllegalStateException e) {
            // Killing the Timer thread is equivalent to cancelling the Timer
        }
    }
}

