import java.net.UnknownHostException;
import java.net.InetAddress;

public class TplClass6965 {

    private static final void method() throws Throwable {
        String host = "999.999.999.999";
        boolean exc_thrown = false;
        try {
            InetAddress ia = InetAddress.getByName(host);
        } catch (UnknownHostException e) {
            exc_thrown = true;
        }
        if (!exc_thrown) {
        }
        host = "[]";
        exc_thrown = false;
        try {
            InetAddress ia = InetAddress.getByName(host);
        } catch (UnknownHostException e) {
            exc_thrown = true;
        } catch (Exception e) {
        }
        if (!exc_thrown) {
        }
        host = "[127.0.0.1]";
        exc_thrown = false;
        try {
            InetAddress ia = InetAddress.getByName(host);
        } catch (UnknownHostException e) {
            exc_thrown = true;
        } catch (Exception e) {
        }
        if (!exc_thrown) {
        }
        host = "[localhost]";
        exc_thrown = false;
        try {
            InetAddress ia = InetAddress.getByName(host);
        } catch (UnknownHostException e) {
            exc_thrown = true;
        } catch (Exception e) {
        }
        if (!exc_thrown) {
        }
    }
}

