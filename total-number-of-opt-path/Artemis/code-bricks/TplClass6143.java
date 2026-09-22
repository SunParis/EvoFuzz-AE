import java.net.Socket;
import java.net.ServerSocket;
import java.net.DatagramSocket;

public class TplClass6143 {

    private static final void method(java.net.ServerSocket ss, java.net.DatagramSocket dsock1, java.net.Socket sock1) throws Throwable {
        if (sock1 != null)
            sock1.close();
        if (ss != null)
            ss.close();
        if (dsock1 != null)
            dsock1.close();
    }
}

