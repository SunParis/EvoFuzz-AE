import java.util.TimerTask;
import java.util.Timer;
import java.util.Random;

public class TplClass5431 {

    private static final void method(java.util.Timer timer, int cancelled, long i, java.util.Random rnd) throws Throwable {
        TimerTask task = new TimerTask() {

            public void run() {
            }
        };
        // i hrs. hence.
        timer.schedule(task, i * 60 * 60 * 1000);
        if (i != 1 && rnd.nextBoolean()) {
            task.cancel();
            cancelled++;
        }
    }
}

