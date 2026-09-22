import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.DatagramSocket;

public class TplClass6117 {

    private static final void method(java.lang.String s1_type, java.net.InetAddress ia1, java.net.DatagramSocket dsock1, int port) throws Throwable {
        if (s1_type.equals("DatagramSocket")) {
            dsock1 = new DatagramSocket(new InetSocketAddress(ia1, 0));
            port = dsock1.getLocalPort();
        }
    }
}

