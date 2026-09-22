import java.io.InputStream;
import java.net.Socket;

public class TplClass6838 {

    private static final void method(java.net.Socket server) throws Throwable {
        InputStream in = server.getInputStream();
        /*
             * This read should throw a SocketException indicating a
             * connection reset.
             */
        int n = in.read();
    }
}

