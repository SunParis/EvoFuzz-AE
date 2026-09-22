import java.net.NetworkInterface;
import java.net.InetAddress;
import java.io.IOException;

public class TplClass6999 {

    private static final void method() throws Throwable {
        try {
            InetAddress addr = InetAddress.getByName("localhost");
            if (!addr.isReachable(10000))
                ;
            NetworkInterface inf = NetworkInterface.getByInetAddress(addr);
            if (inf != null) {
                if (!addr.isReachable(inf, 20, 10000))
                    ;
            }
        } catch (IOException e) {
        }
    }
}

