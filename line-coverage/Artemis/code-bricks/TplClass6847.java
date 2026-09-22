import java.net.Socket;

public class TplClass6847 {

    private static final void method(int port) throws Throwable {
        Socket sock = new Socket((String) null, port);
        sock.close();
        sock = new Socket((String) null, port, true);
        sock.close();
        sock = new Socket((String) null, port, null, 0);
        sock.close();
    }
}

