import java.net.InetAddress;
import java.util.List;

public class TplClass4772 {

    private static final void method(java.net.InetAddress inetAddress, java.util.List<java.lang.String> addrs) throws Throwable {
        for (String addr : addrs) {
            inetAddress = InetAddress.getByName(addr);
            boolean isReachable = inetAddress.isReachable(3000);
            if (isReachable) {
            } else {
            }
        }
    }
}

