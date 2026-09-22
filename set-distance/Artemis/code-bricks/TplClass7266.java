import java.net.ServerSocket;
import java.net.InetAddress;
import java.net.SocketAddress;

public class TplClass7266 {

    private static final void method(java.net.ServerSocket ss, int failCount) throws Throwable {
        // Before Close
        InetAddress ssInetAddress = ss.getInetAddress();
        int ssLocalPort = ss.getLocalPort();
        SocketAddress ssLocalSocketAddress = ss.getLocalSocketAddress();
        // After Close
        ss.close();
        if (ssLocalPort != ss.getLocalPort()) {
            failCount++;
        }
        if (!ss.getInetAddress().equals(ssInetAddress)) {
            failCount++;
        }
        if (!ss.getLocalSocketAddress().equals(ssLocalSocketAddress)) {
            failCount++;
        }
        if (!ss.isBound()) {
            failCount++;
        }
    }
}

