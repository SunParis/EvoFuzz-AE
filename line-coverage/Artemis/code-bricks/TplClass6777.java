import java.net.SocketAddress;
import java.net.DatagramSocket;

public class TplClass6777 {

    private static final void method(boolean ok, java.net.SocketAddress addr, java.net.DatagramSocket ds) throws Throwable {
        try {
            ds.bind(addr);
        } catch (IllegalArgumentException e) {
            ok = true;
        } catch (Exception e2) {
        }
    }
}

