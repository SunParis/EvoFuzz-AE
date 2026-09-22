import java.net.Socket;
import java.net.InetAddress;

public class TplClass6754 {

    private static final void method(java.net.InetAddress addr, int port) throws Throwable {
        try {
            Socket s = new Socket(addr, port);
            s.close();
        } catch (Exception e) {
        }
    }
}

