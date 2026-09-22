import java.net.UnknownHostException;
import java.net.InetAddress;

public class TplClass7820 {

    private static final void method(java.net.InetAddress broadcast2, java.net.InetAddress broadcast1) throws Throwable {
        try {
            broadcast1 = InetAddress.getByName("255.255.255.0");
            broadcast2 = InetAddress.getByName("255.255.0.0");
        } catch (UnknownHostException e) {
        }
    }
}

