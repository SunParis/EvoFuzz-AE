import java.nio.channels.Selector;
import java.io.IOException;

public class TplClass6720 {

    private static final void method() throws Throwable {
        final Selector sel = Selector.open();
        Runnable r = new Runnable() {

            public void run() {
                try {
                    sel.select();
                } catch (IOException x) {
                }
            }
        };
        // start thread to block in Selector
        Thread t = new Thread(r);
        t.start();
        // give thread time to start
        Thread.sleep(1000);
        // interrupt, close, and wakeup is the magic sequence to provoke the NPE
        t.interrupt();
        sel.close();
        sel.wakeup();
    }
}

