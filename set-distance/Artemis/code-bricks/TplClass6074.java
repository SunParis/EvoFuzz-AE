import java.io.InputStream;
import java.net.SocketException;
import java.util.concurrent.Phaser;

public class TplClass6074 {

    private static final void method(java.util.concurrent.Phaser phaser, java.io.InputStream is) throws Throwable {
        try {
            phaser.arriveAndAwaitAdvance();
            while (is.read() != -1) Thread.sleep(50);
        } catch (Exception x) {
            if (!(x instanceof SocketException && x.getMessage().equalsIgnoreCase("socket closed")))
                ;
            // ok, expect Socket closed
        }
    }
}

