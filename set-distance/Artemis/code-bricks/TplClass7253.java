import java.net.SocketTimeoutException;
import java.net.ServerSocket;

public class TplClass7253 {

    private static final void method(java.net.ServerSocket socket) throws Throwable {
        // passes.
        try {
            socket.accept();
        } catch (SocketTimeoutException e) {
        }
    }
}

