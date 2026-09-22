import java.nio.channels.SocketChannel;
import java.nio.channels.ClosedChannelException;

public class TplClass5013 {

    private static final void method(java.nio.channels.SocketChannel sc) throws Throwable {
        try {
            sc.getRemoteAddress();
        } catch (ClosedChannelException e) {
        }
    }
}

