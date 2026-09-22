import java.net.ServerSocket;
import java.net.Socket;
import java.io.InputStream;
import java.io.OutputStream;

public class TplClass6811 {

    private static final void method(java.net.ServerSocket listener, java.net.Socket server, boolean isServer, java.io.InputStream sis, java.io.OutputStream sos) throws Throwable {
        if (isServer) {
            server = listener.accept();
            sis = server.getInputStream();
            sos = server.getOutputStream();
        }
    }
}

