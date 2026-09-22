import java.nio.channels.Selector;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.net.ServerSocket;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.channels.ServerSocketChannel;

public class TplClass6461 {

    private static final void method() throws Throwable {
        ServerSocketChannel server = ServerSocketChannel.open();
        ServerSocket s = server.socket();
        s.bind(new InetSocketAddress(0));
        int port = s.getLocalPort();
        SocketChannel client = SocketChannel.open();
        client.connect(new InetSocketAddress("127.0.0.1", port));
        SocketChannel slave = server.accept();
        slave.configureBlocking(true);
        Selector selector = Selector.open();
        client.configureBlocking(false);
        SelectionKey key = client.register(selector, SelectionKey.OP_READ, null);
        client.close();
        int nb = slave.read(ByteBuffer.allocate(1024));
        selector.close();
        server.close();
    }
}

