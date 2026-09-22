import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.DatagramSocket;

public class TplClass6120 {

    private static final void method(java.lang.String s2_type, java.net.InetAddress ia2, int port) throws Throwable {
        if (s2_type.equals("DatagramSocket")) {
            try (DatagramSocket ds = new DatagramSocket(new InetSocketAddress(ia2, port))) {
            }
        }
    }
}

