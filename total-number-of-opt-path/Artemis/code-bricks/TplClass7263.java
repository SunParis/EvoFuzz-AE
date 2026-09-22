import java.net.ServerSocket;
import java.net.InetAddress;

public class TplClass7263 {

    private static final void method(java.net.ServerSocket ss, int failCount, java.net.InetAddress ssInetAddress) throws Throwable {
        if (!ss.getInetAddress().equals(ssInetAddress)) {
            failCount++;
        }
    }
}

