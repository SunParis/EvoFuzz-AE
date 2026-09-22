import java.net.SocketAddress;
import java.net.DatagramPacket;

public class TplClass6776 {

    private static final void method(java.net.DatagramPacket pac, boolean ok, java.net.SocketAddress addr) throws Throwable {
        try {
            pac.setSocketAddress(addr);
        } catch (IllegalArgumentException e) {
            ok = true;
        } catch (Exception e2) {
        }
    }
}

