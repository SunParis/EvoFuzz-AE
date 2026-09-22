import java.net.InetAddress;

public class TplClass6988 {

    private static final void method(java.net.InetAddress IPv6Loopback, java.net.InetAddress IPv4Loopback) throws Throwable {
        IPv4Loopback = InetAddress.getByAddress(new byte[] { 0x7F, 0x00, 0x00, 0x01 });
        IPv6Loopback = InetAddress.getByAddress(new byte[] { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x01 });
    }
}

