import java.net.ServerSocket;
import java.net.InetAddress;

public class TplClass6119 {

    private static final void method(java.lang.String s2_type, java.net.InetAddress ia2, int port) throws Throwable {
        if (s2_type.equals("ServerSocket")) {
            try (ServerSocket ss2 = new ServerSocket(port, 0, ia2)) {
            }
        }
    }
}

