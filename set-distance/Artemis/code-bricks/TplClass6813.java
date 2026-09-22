import java.net.Socket;
import java.io.InputStream;
import java.io.OutputStream;

public class TplClass6813 {

    private static final void method(java.net.Socket server, boolean isServer, java.lang.String s, java.io.InputStream sis, java.io.OutputStream sos) throws Throwable {
        if (isServer) {
            for (int y = 0; y < s.length(); y++) {
                int c = sis.read();
                if (c != (int) s.charAt(y)) {
                }
            }
            // Do the same from server to client
            sos.write("Hello".getBytes());
            server.sendUrgentData(101);
            sos.write("World".getBytes());
        }
    }
}

