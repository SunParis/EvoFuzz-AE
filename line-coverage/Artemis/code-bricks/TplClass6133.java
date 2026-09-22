import java.net.Socket;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class TplClass6133 {

    private static final void method(int port, java.net.InetAddress ia2) throws Throwable {
        try (Socket sock2 = new Socket()) {
            sock2.bind(new InetSocketAddress(ia2, port));
        }
    }
}

