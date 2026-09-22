import java.nio.channels.ClosedChannelException;
import java.nio.channels.SocketChannel;

public class TplClass5021 {

    private static final void method(java.nio.channels.SocketChannel sc) throws Throwable {
        try {
            sc.socket().getInputStream().read(new byte[100]);
        } catch (ClosedChannelException expected) {
        }
    }
}

