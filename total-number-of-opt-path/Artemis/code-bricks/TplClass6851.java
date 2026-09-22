import java.net.InetSocketAddress;
import java.util.Arrays;
import java.net.InetAddress;
import java.net.Socket;

public class TplClass6851 {

    private static final void method(byte[] bad, int port) throws Throwable {
        InetSocketAddress isa = new InetSocketAddress(InetAddress.getLocalHost(), port);
        Socket s = new Socket();
        s.connect(isa, 1000);
        // if this comes back as 0.0. 0.0 this would demonstrate issue
        InetAddress iaLocal = s.getLocalAddress();
        String sLocalHostname = iaLocal.getHostName();
        if (Arrays.equals(iaLocal.getAddress(), bad)) {
        }
    }
}

