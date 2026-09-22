import java.io.InputStream;
import java.net.Socket;

public class TplClass5321 {

    private static final void method(int port) throws Throwable {
        Socket socket = new Socket("localhost", port);
        InputStream is = socket.getInputStream();
        is.read();
        socket.close();
        is.read();
    }
}

