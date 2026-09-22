import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;

public class TplClass6731 {

    private static final void method(int LINGER) throws Throwable {
        int value;
        InetAddress addr = InetAddress.getLocalHost();
        ServerSocket ss = new ServerSocket(0);
        int port = ss.getLocalPort();
        Socket s = new Socket(addr, port);
        Socket soc = ss.accept();
        soc.setSoLinger(true, LINGER);
        value = soc.getSoLinger();
        soc.close();
        s.close();
        ss.close();
        if (value != 65535)
            ;
    }
}

