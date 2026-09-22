import java.net.Inet4Address;
import java.net.InetAddress;

public class TplClass6980 {

    private static final void method(boolean hasIPv4Address, boolean failed, java.net.InetAddress lh, boolean preferIPv6Addresses) throws Throwable {
        if (!preferIPv6Addresses && hasIPv4Address) {
            if (!(lh instanceof Inet4Address))
                failed = true;
        }
    }
}

