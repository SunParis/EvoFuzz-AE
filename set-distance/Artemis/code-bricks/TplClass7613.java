import java.net.InetAddress;
import java.util.List;
import java.net.UnknownHostException;

public class TplClass7613 {

    private static final void method(java.util.List<java.lang.String> failedAddrs, java.lang.String addrStr) throws Throwable {
        try {
            InetAddress addr = InetAddress.getByName(addrStr);
            // it is an error if no exception
            failedAddrs.add(addrStr);
        } catch (UnknownHostException e) {
            // expected
        }
    }
}

