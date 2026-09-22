import java.net.SocketException;
import java.net.Socket;
import java.net.ServerSocket;

public class TplClass5222 {

    private static final void method() throws Throwable {
        ServerSocket ss = new ServerSocket(0);
        Socket s1 = new Socket("localhost", ss.getLocalPort());
        Socket s2 = ss.accept();
        s1.close();
        boolean exc_thrown = false;
        try {
            s1.setSoTimeout(1000);
        } catch (SocketException e) {
            exc_thrown = true;
        }
        if (!exc_thrown) {
        }
    }
}

