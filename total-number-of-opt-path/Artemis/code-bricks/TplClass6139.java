import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.DatagramSocket;

public class TplClass6139 {

    private static final void method(java.net.InetAddress ia1, java.net.DatagramSocket dsock1, int port) throws Throwable {
        dsock1 = new DatagramSocket(new InetSocketAddress(ia1, 0));
        port = dsock1.getLocalPort();
    }
}

