import java.net.Socket;
import java.net.ServerSocket;

public class TplClass6829 {

    private static final void method(java.net.ServerSocket listener, java.net.Socket server, java.net.Socket client) throws Throwable {
        if (listener != null)
            listener.close();
        if (client != null)
            client.close();
        if (server != null)
            server.close();
    }
}

