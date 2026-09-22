import java.net.DatagramPacket;
import java.net.InetAddress;

public class TplClass5297 {

    private static final void method(java.net.InetAddress host, byte[] buf) throws Throwable {
        /* negative port */
        new DatagramPacket(buf, 100, host, -1);
    }
}

