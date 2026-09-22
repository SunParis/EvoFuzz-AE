import java.net.SocketAddress;
import java.net.DatagramSocket;

public class TplClass6778 {

    private static final void method(boolean ok, java.net.SocketAddress addr, java.net.DatagramSocket ds) throws Throwable {
        try {
            ds.connect(addr);
        } catch (IllegalArgumentException e) {
            ok = true;
        } catch (Exception e2) {
        }
    }
}

