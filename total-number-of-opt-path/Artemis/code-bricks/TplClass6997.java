import java.net.NetworkInterface;
import java.net.InetAddress;

public class TplClass6997 {

    private static final void method(java.net.NetworkInterface inf, java.net.InetAddress addr) throws Throwable {
        if (inf != null) {
            if (!addr.isReachable(inf, 20, 10000))
                ;
        }
    }
}

