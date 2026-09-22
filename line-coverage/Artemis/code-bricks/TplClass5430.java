import java.util.TimerTask;
import java.util.Timer;
import java.util.Random;

public class TplClass5430 {

    private static final void method() throws Throwable {
        Random rnd = new Random();
        Timer timer = new Timer();
        int cancelled = 0;
        for (long i = 1; i <= 1000; i++) {
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
        int purged = timer.purge();
        if (purged != cancelled)
            ;
        purged = timer.purge();
        if (purged != 0)
            ;
        timer.cancel();
    }
}

