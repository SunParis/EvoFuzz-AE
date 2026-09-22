import java.io.InputStream;
import java.net.SocketException;
import java.util.concurrent.Phaser;

public class TplClass6078 {

    private static final void method(java.util.concurrent.Phaser phaser, java.lang.Thread[] threads, java.io.InputStream is, int j) throws Throwable {
        threads[j] = new Thread() {

            public void run() {
                try {
                    phaser.arriveAndAwaitAdvance();
                    while (is.read() != -1) Thread.sleep(50);
                } catch (Exception x) {
                    if (!(x instanceof SocketException && x.getMessage().equalsIgnoreCase("socket closed")))
                        ;
                    // ok, expect Socket closed
                }
            }
        };
    }
}

