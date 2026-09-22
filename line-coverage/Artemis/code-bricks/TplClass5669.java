import java.util.concurrent.CountDownLatch;
import java.util.Random;
import java.io.IOException;
import java.nio.channels.AsynchronousSocketChannel;

public class TplClass5669 {

    private static final void method(java.util.Random rand, java.util.concurrent.CountDownLatch latch, java.nio.channels.AsynchronousSocketChannel ch) throws Throwable {
        try {
            ch.close();
        } catch (IOException ignore) {
        }
        latch.countDown();
        // throw error or runtime exception
        if (rand.nextBoolean()) {
        } else {
        }
    }
}

