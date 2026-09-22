import java.net.Socket;
import java.io.OutputStream;

public class TplClass6824 {

    private static final void method(java.net.Socket client, java.io.OutputStream clos) throws Throwable {
        clos.write("Hello".getBytes());
        client.sendUrgentData(100);
        clos.write("world".getBytes());
    }
}

