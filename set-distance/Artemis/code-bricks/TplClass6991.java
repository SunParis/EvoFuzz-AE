import java.net.InetAddress;
import java.net.NetworkInterface;

public class TplClass6991 {

    private static final void method(java.net.NetworkInterface inf, java.net.InetAddress addr, java.net.InetAddress remoteAddr) throws Throwable {
        if (inf != null) {
            if (!addr.isReachable(inf, 20, 10000)) {
            } else {
            }
            if (remoteAddr.isReachable(inf, 20, 10000)) {
            } else {
            }
        } else {
        }
    }
}

