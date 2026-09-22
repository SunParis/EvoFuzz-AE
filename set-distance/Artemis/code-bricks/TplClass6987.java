import java.net.InetAddress;
import java.net.UnknownHostException;

public class TplClass6987 {

    private static final void method(java.net.InetAddress IPv6Loopback, java.net.InetAddress IPv4Loopback) throws Throwable {
        try {
            IPv4Loopback = InetAddress.getByAddress(new byte[] { 0x7F, 0x00, 0x00, 0x01 });
            IPv6Loopback = InetAddress.getByAddress(new byte[] { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x01 });
        } catch (UnknownHostException e) {
        }
    }
}

