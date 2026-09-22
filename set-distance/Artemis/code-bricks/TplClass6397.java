import java.io.IOException;
import java.nio.channels.SocketChannel;

public class TplClass6397 {

    private static final void method(java.nio.channels.SocketChannel client) throws Throwable {
        try {
            for (int i = 0; i < 256; i++) client.socket().sendUrgentData(i);
        } catch (IOException ioe) {
        }
    }
}

