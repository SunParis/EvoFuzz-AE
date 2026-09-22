import java.net.SocketException;
import java.net.Socket;

public class TplClass5221 {

    private static final void method(boolean exc_thrown, java.net.Socket s1) throws Throwable {
        try {
            s1.setSoTimeout(1000);
        } catch (SocketException e) {
            exc_thrown = true;
        }
    }
}

