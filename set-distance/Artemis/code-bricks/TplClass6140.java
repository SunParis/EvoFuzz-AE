import java.net.Socket;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class TplClass6140 {

    private static final void method(java.net.InetAddress ia2, int port, java.net.Socket sock2) throws Throwable {
        sock2.bind(new InetSocketAddress(ia2, port));
    }
}

