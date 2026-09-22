import java.net.DatagramPacket;
import java.net.InetAddress;

public class TplClass5287 {

    private static final void method(java.net.InetAddress host, byte[] buf, boolean error) throws Throwable {
        try {
            /* negative port */
            new DatagramPacket(buf, 100, host, -1);
        } catch (IllegalArgumentException e) {
            /* correct exception */
            error = false;
        }
    }
}

