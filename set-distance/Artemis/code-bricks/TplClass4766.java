import java.net.InetAddress;
import java.util.Vector;

public class TplClass4766 {

    private static final void method(boolean result, int i, java.net.InetAddress addr, java.util.Vector v, java.lang.String[][] addrs) throws Throwable {
        if (addr.isSiteLocalAddress() != result) {
            v.add(addrs[i]);
        }
    }
}

