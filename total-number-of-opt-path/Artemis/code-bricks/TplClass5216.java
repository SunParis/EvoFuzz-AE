import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;

public class TplClass5216 {

    private static final void method() throws Throwable {
        ServerSocket soc = null;
        Socket csoc = null;
        int port = 0;
        try {
            soc = new ServerSocket(port, Integer.MAX_VALUE);
            port = soc.getLocalPort();
        } catch (Exception e) {
        }
        try {
            csoc = new Socket(InetAddress.getLocalHost(), port);
        } catch (Exception e) {
        }
        try {
            soc.close();
            csoc.close();
        } catch (Exception e) {
        }
    }
}

