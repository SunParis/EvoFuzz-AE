import java.nio.channels.Selector;
import java.io.IOException;

public class TplClass6691 {

    private static final void method(boolean closed, boolean awakened) throws Throwable {
        final Selector selector = Selector.open();
        // Create and start a selector in a separate thread.
        new Thread(new Runnable() {

            public void run() {
                try {
                    selector.select();
                    awakened = true;
                } catch (IOException e) {
                }
            }
        }).start();
        // Wait for above thread to get to select() before we call close.
        Thread.sleep(3000);
        // Try to close. This should wakeup select.
        new Thread(new Runnable() {

            public void run() {
                try {
                    selector.close();
                    closed = true;
                } catch (IOException e) {
                }
            }
        }).start();
        // Wait for select() to be awakened, which should be done by close.
        Thread.sleep(3000);
        if (!awakened)
            selector.wakeup();
        // Correct result is true and true
        if (!awakened)
            ;
        if (!closed)
            ;
    }
}

