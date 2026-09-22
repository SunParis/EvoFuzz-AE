import java.net.Socket;
import java.io.OutputStream;

public class TplClass6812 {

    private static final void method(java.net.Socket client, boolean isClient, java.io.OutputStream clos) throws Throwable {
        if (isClient) {
            clos.write("Hello".getBytes());
            client.sendUrgentData(100);
            clos.write("world".getBytes());
        }
    }
}

