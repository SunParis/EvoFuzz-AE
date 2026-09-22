import java.net.InetAddress;
import java.util.List;

public class TplClass7615 {

    private static final void method(java.util.List<java.lang.String> failedAddrs, java.lang.String addrStr) throws Throwable {
        InetAddress addr = InetAddress.getByName(addrStr);
        // it is an error if no exception
        failedAddrs.add(addrStr);
    }
}

