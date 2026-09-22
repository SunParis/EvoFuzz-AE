import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.Socket;

public class TplClass6749 {

    private static final void method(boolean readTimedOut, java.net.Socket s2) throws Throwable {
        try {
            s2.getInputStream().read();
        } catch (SocketTimeoutException te) {
            readTimedOut = true;
        } catch (SocketException e) {
            if (!s2.isClosed()) {
            }
        }
    }
}

