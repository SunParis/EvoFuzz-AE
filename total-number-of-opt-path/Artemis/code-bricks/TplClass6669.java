import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class TplClass6669 {

    private static final void method(java.nio.channels.SocketChannel sc, java.nio.channels.ServerSocketChannel ssc, java.nio.channels.SocketChannel peer) throws Throwable {
        if (peer != null)
            peer.close();
        if (sc != null)
            sc.close();
        if (ssc != null)
            ssc.close();
    }
}

