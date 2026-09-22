import java.net.InetAddress;
import java.util.Vector;

public class TplClass4769 {

    private static final void method(int i, java.util.Vector v, java.lang.String[][] addrs) throws Throwable {
        InetAddress addr = InetAddress.getByName(addrs[i][0]);
        boolean result = new Boolean(addrs[i][1]).booleanValue();
        if (addr.isSiteLocalAddress() != result) {
            v.add(addrs[i]);
        }
    }
}

