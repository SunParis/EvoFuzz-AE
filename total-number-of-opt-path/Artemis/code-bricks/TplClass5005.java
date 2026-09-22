import java.net.NetworkInterface;
import java.net.InetSocketAddress;

public class TplClass5005 {

    private static final void method(java.net.InetSocketAddress local) throws Throwable {
        if (!local.getAddress().isAnyLocalAddress()) {
            if (NetworkInterface.getByInetAddress(local.getAddress()) == null)
                ;
        }
    }
}

