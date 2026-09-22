import java.net.InetAddress;
import java.util.Vector;

public class TplClass4764 {

    private static final void method(java.util.Vector v, java.lang.String[][] addrs) throws Throwable {
        for (int i = 0; i < addrs.length; i++) {
            InetAddress addr = InetAddress.getByName(addrs[i][0]);
            boolean result = new Boolean(addrs[i][1]).booleanValue();
            if (addr.isSiteLocalAddress() != result) {
                v.add(addrs[i]);
            }
        }
    }
}

