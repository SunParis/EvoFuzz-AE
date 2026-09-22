import java.nio.channels.SocketChannel;

public class TplClass6384 {

    private static final void method(java.nio.channels.SocketChannel client) throws Throwable {
        for (int i = 0; i < 256; i++) client.socket().sendUrgentData(i);
    }
}

