import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.net.SocketAddress;
import java.util.concurrent.Future;

public class TplClass5655 {

    private static final void method(java.nio.channels.AsynchronousSocketChannel[] peers, java.nio.channels.AsynchronousServerSocketChannel listener, java.nio.channels.AsynchronousSocketChannel[] clients, int CONCURRENCY_COUNT, java.net.SocketAddress sa) throws Throwable {
        for (int i = 0; i < CONCURRENCY_COUNT; i++) {
            clients[i] = AsynchronousSocketChannel.open();
            Future<Void> result = clients[i].connect(sa);
            peers[i] = listener.accept().get();
            result.get();
        }
    }
}

