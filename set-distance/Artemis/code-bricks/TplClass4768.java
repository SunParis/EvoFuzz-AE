import java.net.InetAddress;
import java.util.Vector;
import java.util.Iterator;

public class TplClass4768 {

    private static final void method() throws Throwable {
        String[][] addrs = { { "9.255.255.255", "false" }, { "10.0.0.0", "true" }, { "10.255.255.255", "true" }, { "11.0.0.0", "false" }, { "172.15.255.255", "false" }, { "172.16.0.0", "true" }, { "172.30.1.2", "true" }, { "172.31.255.255", "true" }, { "172.32.0.0", "false" }, { "192.167.255.255", "false" }, { "192.168.0.0", "true" }, { "192.168.255.255", "true" }, { "192.169.0.0", "false" } };
        Vector v = new Vector();
        for (int i = 0; i < addrs.length; i++) {
            InetAddress addr = InetAddress.getByName(addrs[i][0]);
            boolean result = new Boolean(addrs[i][1]).booleanValue();
            if (addr.isSiteLocalAddress() != result) {
                v.add(addrs[i]);
            }
        }
        Iterator itr = v.iterator();
        while (itr.hasNext()) {
            String[] entry = (String[]) itr.next();
        }
        if (v.size() > 0) {
        }
    }
}

