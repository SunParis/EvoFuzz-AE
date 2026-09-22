import java.net.Socket;
import java.net.ServerSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.DatagramSocket;

public class TplClass6136 {

    private static final void method(java.lang.String s1_type, java.net.ServerSocket ss, java.net.DatagramSocket dsock1, int port, java.net.Socket sock1, java.net.InetAddress ia1, java.lang.String s2_type, java.net.InetAddress ia2) throws Throwable {
        if (s1_type.equals("Socket")) {
            sock1 = new Socket();
            sock1.bind(new InetSocketAddress(ia1, 0));
            port = sock1.getLocalPort();
        }
        if (s1_type.equals("ServerSocket")) {
            ss = new ServerSocket(0, 0, ia1);
            port = ss.getLocalPort();
        }
        if (s1_type.equals("DatagramSocket")) {
            dsock1 = new DatagramSocket(new InetSocketAddress(ia1, 0));
            port = dsock1.getLocalPort();
        }
        if (s2_type.equals("Socket")) {
            try (Socket sock2 = new Socket()) {
                sock2.bind(new InetSocketAddress(ia2, port));
            }
        }
        if (s2_type.equals("ServerSocket")) {
            try (ServerSocket ss2 = new ServerSocket(port, 0, ia2)) {
            }
        }
        if (s2_type.equals("DatagramSocket")) {
            try (DatagramSocket ds = new DatagramSocket(new InetSocketAddress(ia2, port))) {
            }
        }
    }
}

