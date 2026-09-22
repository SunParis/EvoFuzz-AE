import java.net.Socket;
import java.io.InputStream;
import java.net.SocketException;
import java.util.concurrent.Phaser;

public class TplClass6077 {

    private static final void method(java.util.concurrent.Phaser phaser, java.net.Socket s, int THREADS, java.net.Socket sa) throws Throwable {
        sa.setSoLinger(false, 0);
        final InputStream is = s.getInputStream();
        Thread[] threads = new Thread[THREADS];
        for (int j = 0; j < THREADS; j++) {
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
        for (int j = 0; j < 100; j++) threads[j].start();
        phaser.arriveAndAwaitAdvance();
        s.close();
        for (int j = 0; j < 100; j++) threads[j].join();
    }
}

