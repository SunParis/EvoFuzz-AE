import java.net.InetAddress;
import java.net.Inet6Address;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class TplClass5347 {

    private static final void method(java.util.Enumeration e) throws Throwable {
        while (e.hasMoreElements()) {
            NetworkInterface ifc = (NetworkInterface) e.nextElement();
            Enumeration addrs = ifc.getInetAddresses();
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
}

