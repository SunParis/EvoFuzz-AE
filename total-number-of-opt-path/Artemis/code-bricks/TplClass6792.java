import java.net.Socket;
import java.net.ServerSocket;

public class TplClass6792 {

    private static final void method(java.net.ServerSocket ss, java.net.Socket s1, java.net.Socket s2) throws Throwable {
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

