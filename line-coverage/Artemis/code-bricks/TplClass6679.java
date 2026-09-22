import java.net.SocketTimeoutException;
import java.net.Socket;

public class TplClass6679 {

    private static final void method(java.net.Socket socket, byte[] buffer) throws Throwable {
        // to trigger this bug
        try {
            socket.getInputStream().read(buffer);
        } catch (java.net.SocketTimeoutException ste) {
            // no java.nio.channels.ClosedSelectorException
        }
    }
}

