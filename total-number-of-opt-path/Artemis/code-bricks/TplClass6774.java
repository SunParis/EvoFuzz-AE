import java.net.SocketAddress;
import java.net.Socket;

public class TplClass6774 {

    private static final void method(boolean ok, java.net.SocketAddress addr, java.net.Socket soc) throws Throwable {
        try {
            soc.connect(addr, 100);
        } catch (IllegalArgumentException e) {
            ok = true;
        } catch (Exception e2) {
        }
    }
}

