import java.nio.channels.SelectionKey;
import java.util.Set;
import java.net.InetSocketAddress;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.net.InetAddress;

public class TplClass6662 {

    private static final void method() throws Throwable {
        ServerSocketChannel ssc = null;
        SocketChannel sc = null;
        SocketChannel peer = null;
        try {
            ssc = ServerSocketChannel.open().bind(new InetSocketAddress(0));
            // loopback connection
            InetAddress lh = InetAddress.getLocalHost();
            sc = SocketChannel.open(new InetSocketAddress(lh, ssc.socket().getLocalPort()));
            peer = ssc.accept();
            // peer sends message so that "sc" will be readable
            int n = peer.write(ByteBuffer.wrap("Hello".getBytes()));
            sc.configureBlocking(false);
            Selector selector = Selector.open();
            SelectionKey key = sc.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
            boolean done = false;
            int failCount = 0;
            while (!done) {
                int nSelected = selector.select();
                if (nSelected > 0) {
                    if (nSelected > 1)
                        ;
                    Set<SelectionKey> keys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = keys.iterator();
                    while (iterator.hasNext()) {
                        key = iterator.next();
                        iterator.remove();
                        if (key.isWritable()) {
                            failCount++;
                            if (failCount > 10)
                                ;
                            Thread.sleep(250);
                        }
                        if (key.isReadable()) {
                            done = true;
                        }
                    }
                }
            }
        } finally {
            if (peer != null)
                peer.close();
            if (sc != null)
                sc.close();
            if (ssc != null)
                ssc.close();
        }
    }
}

