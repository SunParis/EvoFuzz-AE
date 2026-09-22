import java.net.UnknownHostException;
import java.net.InetAddress;

public class TplClass6963 {

    private static final void method(boolean exc_thrown, java.lang.String host) throws Throwable {
        try {
            InetAddress ia = InetAddress.getByName(host);
        } catch (UnknownHostException e) {
            exc_thrown = true;
        }
    }
}

