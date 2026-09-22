import java.io.IOException;
import java.nio.channels.CompletionHandler;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.AsynchronousCloseException;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.ByteBuffer;
import java.util.concurrent.CountDownLatch;

public class TplClass5677 {

    private static final void method(boolean shutdownGroup, java.util.concurrent.CountDownLatch latch, boolean closeChannel, java.nio.channels.AsynchronousSocketChannel ch, java.nio.channels.AsynchronousChannelGroup group) throws Throwable {
        // initiate I/O operation that does not complete (successfully)
        ByteBuffer buf = ByteBuffer.allocate(100);
        ch.read(buf, (Void) null, new CompletionHandler<Integer, Void>() {

            public void completed(Integer bytesRead, Void att) {
            }

            public void failed(Throwable exc, Void att) {
                if (!(exc instanceof AsynchronousCloseException))
                    ;
                latch.countDown();
            }
        });
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

