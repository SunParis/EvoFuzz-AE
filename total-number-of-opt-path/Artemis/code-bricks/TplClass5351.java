import java.net.InetAddress;
import java.net.Inet6Address;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class TplClass5351 {

    private static final void method() throws Throwable {
        Enumeration e = NetworkInterface.getNetworkInterfaces();
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

