import java.net.Socket;
import java.io.InputStream;
import java.net.ServerSocket;
import java.io.OutputStream;

public class TplClass6823 {

    private static final void method(java.net.ServerSocket listener, java.net.Socket server, java.io.InputStream sis, java.io.OutputStream sos) throws Throwable {
        server = listener.accept();
        sis = server.getInputStream();
        sos = server.getOutputStream();
    }
}

