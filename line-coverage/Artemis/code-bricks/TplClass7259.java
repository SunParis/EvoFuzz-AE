import java.net.ServerSocket;
import java.net.Socket;

public class TplClass7259 {

    private static final void method(java.net.ServerSocket ss) throws Throwable {
        (new Socket("localhost", ss.getLocalPort())).close();
    }
}

