import java.net.SocketAddress;
import java.net.Socket;

public class TplClass6773 {

    private static final void method(boolean ok, java.net.SocketAddress addr, java.net.Socket soc) throws Throwable {
        try {
            soc.bind(addr);
        } catch (IllegalArgumentException e) {
            ok = true;
        } catch (Exception e2) {
        }
    }
}

