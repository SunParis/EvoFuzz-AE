import java.net.Inet6Address;
import java.net.InetAddress;

public class TplClass6978 {

    private static final void method(boolean hasIPv6Address, boolean failed, java.net.InetAddress lh, boolean preferIPv6Addresses) throws Throwable {
        if (preferIPv6Addresses && hasIPv6Address) {
            if (!(lh instanceof Inet6Address))
                failed = true;
        }
    }
}

