import java.net.DatagramPacket;
import java.net.InetAddress;

public class TplClass5298 {

    private static final void method() throws Throwable {
        boolean error = true;
        byte[] buf = new byte[256];
        InetAddress address = InetAddress.getLocalHost();
        try {
            /* invalid port value */
            new DatagramPacket(buf, 256, address, Integer.MAX_VALUE);
        } catch (IllegalArgumentException e) {
            /* correct exception */
            error = false;
        }
        if (error) {
        }
    }
}

