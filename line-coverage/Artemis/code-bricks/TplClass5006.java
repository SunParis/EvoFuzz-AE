import java.net.NetworkInterface;
import java.net.InetSocketAddress;

public class TplClass5006 {

    private static final void method(java.net.InetSocketAddress local) throws Throwable {
        if (NetworkInterface.getByInetAddress(local.getAddress()) == null)
            ;
    }
}

