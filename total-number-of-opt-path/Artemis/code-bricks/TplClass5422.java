import java.util.TimerTask;
import java.util.Timer;

public class TplClass5422 {

    private static final void method() throws Throwable {
        Thread myThread = new Thread() {
            private Thread tdThread;
            @Override
            public void run() {
                Timer t = new Timer();
                // Start a mean event that kills the timer thread
                t.schedule(new TimerTask() {

                    public void run() {
                        tdThread = Thread.currentThread();
                    }
                }, 0);
                // Wait for mean event to do the deed and thread to die.
                try {
                    do {
                        Thread.sleep(100);
                    } while (tdThread == null);
                } catch (InterruptedException e) {
                }
                try {
                    tdThread.join();
                } catch (InterruptedException e) {
                    return;
                }
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
        };
        myThread.setDaemon(true);
        myThread.start();
    }
}

