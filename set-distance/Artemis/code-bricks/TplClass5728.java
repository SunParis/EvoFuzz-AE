import java.net.Socket;
import java.net.BindException;
import java.net.InetSocketAddress;
import java.net.InetAddress;
import java.net.ServerSocket;

public class TplClass5728 {

    private static final void method() throws Throwable {
        ServerSocket ss = new ServerSocket(0);
        Socket s1 = null, s2 = null;
        try {
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
        } catch (BindException be) {
            if (System.getProperty("sun.net.useExclusiveBind") != null) {
                // exclusive bind, expected exception
            } else {
            }
        } finally {
            if (ss != null)
                ss.close();
            if (s1 != null)
                s1.close();
            if (s2 != null)
                s2.close();
        }
    }
}

