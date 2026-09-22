import java.net.SocketAddress;
import java.net.MulticastSocket;

public class TplClass6781 {

    private static final void method(boolean ok, java.net.SocketAddress addr, java.net.MulticastSocket mul) throws Throwable {
        try {
            mul.leaveGroup(addr, null);
        } catch (IllegalArgumentException e) {
            ok = true;
        } catch (Exception e2) {
        }
    }
}

