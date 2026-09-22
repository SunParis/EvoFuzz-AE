import java.net.InetAddress;

public class TplClass4775 {

    private static final void method(java.net.InetAddress inetAddress, java.lang.String addr) throws Throwable {
        inetAddress = InetAddress.getByName(addr);
        boolean isReachable = inetAddress.isReachable(3000);
        if (isReachable) {
        } else {
        }
    }
}

