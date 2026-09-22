import java.net.Socket;
import java.net.InetAddress;

public class TplClass6737 {

    private static final void method(boolean error, java.net.InetAddress addr, java.net.Socket soc) throws Throwable {
        if (addr.equals(soc.getLocalAddress())) {
            error = false;
        }
    }
}

