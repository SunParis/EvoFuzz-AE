import java.io.InputStream;
import java.util.concurrent.Phaser;

public class TplClass6079 {

    private static final void method(java.util.concurrent.Phaser phaser, java.io.InputStream is) throws Throwable {
        phaser.arriveAndAwaitAdvance();
        while (is.read() != -1) Thread.sleep(50);
    }
}

