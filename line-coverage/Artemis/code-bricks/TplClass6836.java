import java.io.InputStream;
import java.net.Socket;
import java.net.SocketException;

public class TplClass6836 {

    private static final void method(java.net.SocketException exc, java.net.Socket server) throws Throwable {
        try {
            InputStream in = server.getInputStream();
            /*
             * This read should throw a SocketException indicating a
             * connection reset.
             */
            int n = in.read();
        } catch (SocketException se) {
            exc = se;
        }
    }
}

