import java.net.Socket;
import java.net.ConnectException;
import java.io.InputStream;
import java.net.SocketException;
import java.util.concurrent.Phaser;
import java.net.ServerSocket;

public class TplClass6071 {

    private static final void method(int THREADS) throws Throwable {
        try (ServerSocket ss = new ServerSocket(0)) {
            final int port = ss.getLocalPort();
            final Phaser phaser = new Phaser(THREADS + 1);
            for (int i = 0; i < 100; i++) {
                try {
                    final Socket s = new Socket("localhost", port);
                    s.setSoLinger(false, 0);
                    try (Socket sa = ss.accept()) {
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
                } catch (ConnectException e) {
                }
            }
        }
    }
}

