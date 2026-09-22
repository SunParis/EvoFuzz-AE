import java.net.InetAddress;
import java.io.IOException;
import java.net.NetworkInterface;

public class TplClass6994 {

    private static final void method() throws Throwable {
        try {
            InetAddress addr = InetAddress.getByName("localhost");
            InetAddress remoteAddr = InetAddress.getByName("bugs.openjdk.java.net");
            if (!addr.isReachable(10000))
                ;
            NetworkInterface inf = NetworkInterface.getByInetAddress(addr);
            if (inf != null) {
                if (!addr.isReachable(inf, 20, 10000)) {
                } else {
                }
                if (remoteAddr.isReachable(inf, 20, 10000)) {
                } else {
                }
            } else {
            }
        } catch (IOException e) {
        }
    }
}

