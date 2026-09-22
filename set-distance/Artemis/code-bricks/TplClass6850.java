import java.net.InetSocketAddress;
import java.util.Arrays;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;

public class TplClass6850 {

    private static final void method() throws Throwable {
        ServerSocket ss = new ServerSocket(0);
        int port = ss.getLocalPort();
        byte[] bad = { 0, 0, 0, 0 };
        try {
            InetSocketAddress isa = new InetSocketAddress(InetAddress.getLocalHost(), port);
            Socket s = new Socket();
            s.connect(isa, 1000);
            // if this comes back as 0.0. 0.0 this would demonstrate issue
            InetAddress iaLocal = s.getLocalAddress();
            String sLocalHostname = iaLocal.getHostName();
            if (Arrays.equals(iaLocal.getAddress(), bad)) {
            }
        } catch (Exception e) {
        } finally {
            ss.close();
        }
    }
}

