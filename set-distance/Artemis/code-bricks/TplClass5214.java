import java.net.Socket;
import java.net.InetAddress;

public class TplClass5214 {

    private static final void method(int port, java.net.Socket csoc) throws Throwable {
        try {
            csoc = new Socket(InetAddress.getLocalHost(), port);
        } catch (Exception e) {
        }
    }
}

