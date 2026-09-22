import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class TplClass6399 {

    private static final void method(java.nio.channels.SocketChannel sc1, java.nio.channels.ServerSocketChannel ssc, java.nio.channels.SocketChannel sc2) throws Throwable {
        if (sc1 != null)
            sc1.close();
        if (sc2 != null)
            sc2.close();
        if (ssc != null)
            ssc.close();
    }
}

