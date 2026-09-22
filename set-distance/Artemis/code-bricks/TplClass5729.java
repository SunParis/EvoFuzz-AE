import java.net.Socket;
import java.net.InetSocketAddress;
import java.net.InetAddress;
import java.net.ServerSocket;

public class TplClass5729 {

    private static final void method(java.net.ServerSocket ss, java.net.Socket s1, java.net.Socket s2) throws Throwable {
        int port = ss.getLocalPort();
        s1 = new Socket(InetAddress.getLocalHost(), port);
        s2 = ss.accept();
        // close server socket and the accepted connection
        ss.close();
        s2.close();
        ss = new ServerSocket();
        ss.bind(new InetSocketAddress(port));
        ss.close();
        // close the client socket
        s1.close();
    }
}

