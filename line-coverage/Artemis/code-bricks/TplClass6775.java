import java.net.SocketAddress;
import java.net.ServerSocket;

public class TplClass6775 {

    private static final void method(java.net.ServerSocket serv, boolean ok, java.net.SocketAddress addr) throws Throwable {
        try {
            serv.bind(addr);
        } catch (IllegalArgumentException e) {
            ok = true;
        } catch (Exception e2) {
        }
    }
}

