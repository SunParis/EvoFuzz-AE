import java.net.Socket;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class TplClass6118 {

    private static final void method(java.lang.String s2_type, java.net.InetAddress ia2, int port) throws Throwable {
        if (s2_type.equals("Socket")) {
            try (Socket sock2 = new Socket()) {
                sock2.bind(new InetSocketAddress(ia2, port));
            }
        }
    }
}

