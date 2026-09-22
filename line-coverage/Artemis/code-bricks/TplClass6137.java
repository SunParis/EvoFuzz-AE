import java.net.Socket;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class TplClass6137 {

    private static final void method(java.net.InetAddress ia1, int port, java.net.Socket sock1) throws Throwable {
        sock1 = new Socket();
        sock1.bind(new InetSocketAddress(ia1, 0));
        port = sock1.getLocalPort();
    }
}

