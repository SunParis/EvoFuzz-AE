import java.net.InetAddress;
import java.net.Inet6Address;
import java.util.Enumeration;

public class TplClass5348 {

    private static final void method(java.util.Enumeration addrs) throws Throwable {
        while (addrs.hasMoreElements()) {
            InetAddress a = (InetAddress) addrs.nextElement();
            if (a instanceof Inet6Address) {
                Inet6Address ia6 = (Inet6Address) a;
                Object o = ia6.getScopedInterface();
                if (o instanceof String) {
                }
            }
        }
    }
}

