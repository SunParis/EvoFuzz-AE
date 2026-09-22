import java.net.ServerSocket;
import java.net.Socket;

public class TplClass5439 {

    private static final void method() throws Throwable {
        try (ServerSocket ss = new ServerSocket(0);
            Socket s1 = new Socket(ss.getInetAddress(), ss.getLocalPort());
            Socket s2 = ss.accept()) {
        }
    }
}

