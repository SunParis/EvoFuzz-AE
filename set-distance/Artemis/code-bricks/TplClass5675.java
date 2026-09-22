import java.io.IOException;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.CountDownLatch;

public class TplClass5675 {

    private static final void method(boolean shutdownGroup, java.util.concurrent.CountDownLatch latch, boolean closeChannel, java.nio.channels.AsynchronousSocketChannel ch, java.nio.channels.AsynchronousChannelGroup group) throws Throwable {
        // close channel or shutdown group
        try {
            if (closeChannel) {
                ch.close();
            }
            if (shutdownGroup) {
                group.shutdownNow();
            }
            latch.countDown();
        } catch (IOException e) {
        }
    }
}

