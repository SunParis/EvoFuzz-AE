import java.net.Socket;
import java.net.InetAddress;

public class TplClass6756 {

    private static final void method(java.net.InetAddress addr, int port) throws Throwable {
        Socket s = new Socket(addr, port);
        s.close();
    }
}

