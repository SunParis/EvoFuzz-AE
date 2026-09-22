import java.net.InetAddress;

public class TplClass6989 {

    private static final void method(java.net.InetAddress IPv6Loopback, java.net.InetAddress IPv4Loopback) throws Throwable {
        InetAddress addr = InetAddress.getLoopbackAddress();
        if (!(addr.equals(IPv4Loopback) || addr.equals(IPv6Loopback)))
            ;
        InetAddress addr2 = InetAddress.getLoopbackAddress();
        if (addr != addr2)
            ;
    }
}

