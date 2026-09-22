import java.net.Socket;

public class TplClass6835 {

    private static final void method(java.net.Socket client) throws Throwable {
        try {
            // hard reset
            client.setSoLinger(true, 0);
            client.close();
        } catch (Exception e) {
        }
    }
}

