import java.net.Socket;

public class TplClass6837 {

    private static final void method(java.net.Socket client) throws Throwable {
        // hard reset
        client.setSoLinger(true, 0);
        client.close();
    }
}

