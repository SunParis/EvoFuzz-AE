import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.Future;
import java.net.SocketAddress;

public class TplClass5659 {

    private static final void method(java.nio.channels.AsynchronousSocketChannel[] peers, java.nio.channels.AsynchronousServerSocketChannel listener, int i, java.nio.channels.AsynchronousSocketChannel[] clients, java.net.SocketAddress sa) throws Throwable {
        clients[i] = AsynchronousSocketChannel.open();
        Future<Void> result = clients[i].connect(sa);
        peers[i] = listener.accept().get();
        result.get();
    }
}

