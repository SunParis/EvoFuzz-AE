import java.util.TimerTask;
import java.util.Timer;

public class TplClass5425 {

    private static final void method(java.util.Timer t) throws Throwable {
        // Timer thread is dead now
        t.schedule(new TimerTask() {

            public void run() {
            }
        }, 0);
    }
}

