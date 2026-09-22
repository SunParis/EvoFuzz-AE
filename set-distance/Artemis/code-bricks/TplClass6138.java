import java.net.ServerSocket;
import java.net.InetAddress;

public class TplClass6138 {

    private static final void method(java.net.ServerSocket ss, java.net.InetAddress ia1, int port) throws Throwable {
        ss = new ServerSocket(0, 0, ia1);
        port = ss.getLocalPort();
    }
}

