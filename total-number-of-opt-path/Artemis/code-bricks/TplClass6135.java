import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.DatagramSocket;

public class TplClass6135 {

    private static final void method(int port, java.net.InetAddress ia2) throws Throwable {
        try (DatagramSocket ds = new DatagramSocket(new InetSocketAddress(ia2, port))) {
        }
    }
}

