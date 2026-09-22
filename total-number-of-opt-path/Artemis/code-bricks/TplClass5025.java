import java.nio.channels.ClosedChannelException;
import java.nio.channels.ServerSocketChannel;

public class TplClass5025 {

    private static final void method(java.nio.channels.ServerSocketChannel ssc) throws Throwable {
        try {
            ssc.socket().accept();
        } catch (ClosedChannelException expected) {
        }
    }
}

