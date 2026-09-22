import java.net.ServerSocket;
import java.net.SocketAddress;

public class TplClass7264 {

    private static final void method(java.net.ServerSocket ss, java.net.SocketAddress ssLocalSocketAddress, int failCount) throws Throwable {
        if (!ss.getLocalSocketAddress().equals(ssLocalSocketAddress)) {
            failCount++;
        }
    }
}

