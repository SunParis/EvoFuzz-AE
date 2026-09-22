import java.net.DatagramPacket;
import java.net.InetAddress;

public class TplClass5299 {

    private static final void method(byte[] buf, java.net.InetAddress address) throws Throwable {
        /* invalid port value */
        new DatagramPacket(buf, 256, address, Integer.MAX_VALUE);
    }
}

