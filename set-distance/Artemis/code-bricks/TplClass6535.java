import java.net.InetSocketAddress;
import java.net.DatagramPacket;

public class TplClass6535 {

    private static final void method(java.net.InetSocketAddress receiver, java.net.DatagramPacket pkt) throws Throwable {
        // This is legacy behavior
        if (!pkt.getSocketAddress().equals(receiver))
            ;
    }
}

