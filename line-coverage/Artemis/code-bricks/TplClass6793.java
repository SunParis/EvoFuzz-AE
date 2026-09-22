import java.net.Socket;
import java.net.ServerSocket;

public class TplClass6793 {

    private static final void method() throws Throwable {
        ServerSocket ss = new ServerSocket(0);
        Socket s1 = new Socket(ss.getInetAddress(), ss.getLocalPort());
        Socket s2 = ss.accept();
        try {
            s1.shutdownInput();
            // failed b55
            s1.shutdownOutput();
        } finally {
            s1.close();
            s2.close();
            ss.close();
        }
    }
}

