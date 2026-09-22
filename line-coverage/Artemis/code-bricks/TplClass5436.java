import java.util.TimerTask;
import java.util.concurrent.LinkedTransferQueue;
import java.util.Timer;

public class TplClass5436 {

    private static final void method(java.util.Timer timer, java.lang.String expected) throws Throwable {
        LinkedTransferQueue<String> queue = new LinkedTransferQueue<>();
        TimerTask task = new TimerTask() {

            public void run() {
                queue.put(Thread.currentThread().getName());
            }
        };
        // immediately
        timer.schedule(task, 0L);
        String actual = queue.take();
        if (!expected.equals(actual)) {
        }
    }
}

