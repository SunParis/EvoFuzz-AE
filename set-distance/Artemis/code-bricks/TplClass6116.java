import java.net.ServerSocket;
import java.net.InetAddress;

public class TplClass6116 {

    private static final void method(java.net.ServerSocket ss, java.lang.String s1_type, java.net.InetAddress ia1, int port) throws Throwable {
        if (s1_type.equals("ServerSocket")) {
            ss = new ServerSocket(0, 0, ia1);
            port = ss.getLocalPort();
        }
    }
}

