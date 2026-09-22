import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.CountDownLatch;

public class TplClass5679 {

    private static final void method(boolean shutdownGroup, java.util.concurrent.CountDownLatch latch, boolean closeChannel, java.nio.channels.AsynchronousSocketChannel ch, java.nio.channels.AsynchronousChannelGroup group) throws Throwable {
        if (closeChannel) {
            ch.close();
        }
        if (shutdownGroup) {
            group.shutdownNow();
        }
        latch.countDown();
    }
}

