import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;
import java.io.IOException;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.Random;
import java.nio.channels.CompletionHandler;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class TplClass5662 {

    private static final void method(java.util.Random rand, java.nio.channels.AsynchronousServerSocketChannel listener, int count) throws Throwable {
        for (int i = 0; i < count; i++) {
            final CountDownLatch latch = new CountDownLatch(1);
            listener.accept((Void) null, new CompletionHandler<AsynchronousSocketChannel, Void>() {

                public void completed(AsynchronousSocketChannel ch, Void att) {
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

                public void failed(Throwable exc, Void att) {
                }
            });
            // establish loopback connection which should cause completion
            // handler to be invoked.
            int port = ((InetSocketAddress) (listener.getLocalAddress())).getPort();
            AsynchronousSocketChannel ch = AsynchronousSocketChannel.open();
            InetAddress lh = InetAddress.getLocalHost();
            ch.connect(new InetSocketAddress(lh, port)).get();
            ch.close();
            // wait for handler to be invoked
            latch.await();
        }
    }
}

