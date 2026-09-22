import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;

public class TplClass6806 {

    private static final void method() throws Throwable {
        ServerSocket ss;
        Socket s;
        InetAddress ad1, ad2;
        int port1, port2, serverport;
        ss = new ServerSocket(0);
        serverport = ss.getLocalPort();
        s = new Socket("localhost", serverport);
        s.close();
        ss.close();
        ad1 = ss.getInetAddress();
        if (ad1 == null)
            ;
        port1 = ss.getLocalPort();
        if (port1 != serverport)
            ;
        ad2 = s.getInetAddress();
        if (ad2 == null)
            ;
        port2 = s.getPort();
        if (port2 != serverport)
            ;
        ad2 = s.getLocalAddress();
        if (ad2 == null)
            ;
        port2 = s.getLocalPort();
        if (port2 == -1)
            ;
    }
}

