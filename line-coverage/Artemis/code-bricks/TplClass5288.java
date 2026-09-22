import java.net.DatagramPacket;
import java.net.InetAddress;

public class TplClass5288 {

    private static final void method(byte[] buf, java.net.InetAddress address, boolean error) throws Throwable {
        try {
            /* invalid port value */
            new DatagramPacket(buf, 256, address, Integer.MAX_VALUE);
        } catch (IllegalArgumentException e) {
            /* correct exception */
            error = false;
        }
    }
}

