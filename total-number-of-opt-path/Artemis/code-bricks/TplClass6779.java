import java.net.SocketAddress;
import java.net.MulticastSocket;

public class TplClass6779 {

    private static final void method(boolean ok, java.net.SocketAddress addr, java.net.MulticastSocket mul) throws Throwable {
        try {
            mul.bind(addr);
        } catch (IllegalArgumentException e) {
            ok = true;
        } catch (Exception e2) {
        }
    }
}

