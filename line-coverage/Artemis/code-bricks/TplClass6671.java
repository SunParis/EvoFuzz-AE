import java.nio.channels.SocketChannel;
import java.net.SocketAddress;
import java.io.IOException;

public class TplClass6671 {

    private static final void method(java.net.SocketAddress sa) throws Throwable {
        try {
            SocketChannel.open(sa).close();
        } catch (IOException x) {
            // back-off as probably resource related
            try {
                Thread.sleep(10);
            } catch (InterruptedException ignore) {
            }
        }
    }
}

