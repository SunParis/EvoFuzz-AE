import java.util.ArrayList;
import java.net.InetAddress;
import java.util.List;
import java.net.UnknownHostException;

public class TplClass7614 {

    private static final void method() throws Throwable {
        String[] badAddresses = new String[] { // too many :
        // not enough :
        // bad digits
        "0:1:2:3:4:5:6:7:8", // adjacent :
        "0:1:2:3:4:5:6", // too many digits
        "0:1:2:3:4:5:6:x", // compressed, bad digits
        "0:1:2:3:4:5:6::7", // compressed, too many adjacent :
        "0:1:2:3:4:5:6:789abcdef", // compressed, too many digits
        "0:1:2:3::x", // compressed, not enough :
        "0:1:2:::3", // with embeded ipv4, bad ipv6 digits
        "0:1:2:3::abcde", // with embeded ipv4, bad ipv4 digits
        "0:1", // with embeded ipv4, adjacent :
        "0:0:0:0:0:x:10.0.0.1", // with embeded ipv4, too many ipv6 digits
        "0:0:0:0:0:0:10.0.0.x", // with embeded ipv4, too many :
        "0:0:0:0:0::0:10.0.0.1", // with embeded ipv4, not enough :
        "0:0:0:0:0:fffff:10.0.0.1", // with embeded ipv4, too many .
        "0:0:0:0:0:0:0:10.0.0.1", // with embeded ipv4, not enough .
        "0:0:0:0:0:10.0.0.1", // with embeded ipv4, adjacent .
        "0:0:0:0:0:0:10.0.0.0.1", // with compressed ipv4, bad ipv6 digits
        "0:0:0:0:0:0:10.0.1", // with compressed ipv4, bad ipv4 digits
        "0:0:0:0:0:0:10..0.0.1", // with compressed ipv4, too many adjacent :
        "::fffx:192.168.0.1", // with compressed ipv4, too many ipv6 digits
        "::ffff:192.168.0.x", // with compressed ipv4, too many ipv4 digits
        ":::ffff:192.168.0.1", // with compressed ipv4, not enough :
        "::fffff:192.168.0.1", // with compressed ipv4, too many .
        "::ffff:1923.168.0.1", // with compressed ipv4, not enough .
        ":ffff:192.168.0.1", // with compressed ipv4, adjacent .
        "::ffff:192.168.0.1.2", "::ffff:192.168.0", "::ffff:192.168..0.1" };
        List<String> failedAddrs = new ArrayList<String>();
        for (String addrStr : badAddresses) {
            try {
                InetAddress addr = InetAddress.getByName(addrStr);
                // it is an error if no exception
                failedAddrs.add(addrStr);
            } catch (UnknownHostException e) {
                // expected
            }
        }
        if (failedAddrs.size() > 0) {
            for (String addr : failedAddrs) {
            }
        }
    }
}

