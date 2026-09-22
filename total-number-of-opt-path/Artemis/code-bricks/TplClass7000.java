import java.net.NetworkInterface;
import java.net.InetAddress;

public class TplClass7000 {

    private static final void method() throws Throwable {
        InetAddress addr = InetAddress.getByName("localhost");
        if (!addr.isReachable(10000))
            ;
        NetworkInterface inf = NetworkInterface.getByInetAddress(addr);
        if (inf != null) {
            if (!addr.isReachable(inf, 20, 10000))
                ;
        }
    }
}

