import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.CyclicBarrier;

public class TplClass5658 {

    private static final void method(java.nio.channels.AsynchronousSocketChannel ch, java.util.concurrent.CyclicBarrier barrier) throws Throwable {
        try {
            ch.close();
            barrier.await();
        } catch (Exception x) {
        }
    }
}

