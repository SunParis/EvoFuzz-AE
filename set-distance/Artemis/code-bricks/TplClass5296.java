import java.net.DatagramPacket;
import java.net.InetAddress;

public class TplClass5296 {

    private static final void method() throws Throwable {
        boolean error = true;
        byte[] buf = new byte[128];
        InetAddress host = InetAddress.getLocalHost();
        try {
            /* negative port */
            new DatagramPacket(buf, 100, host, -1);
        } catch (IllegalArgumentException e) {
            /* correct exception */
            error = false;
        }
        if (error) {
        }
    }
}

