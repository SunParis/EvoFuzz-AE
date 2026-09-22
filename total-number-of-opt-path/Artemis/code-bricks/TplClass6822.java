import java.net.Socket;
import java.io.InputStream;
import java.io.OutputStream;

public class TplClass6822 {

    private static final void method(java.net.Socket client, java.io.OutputStream clos, java.lang.String clHost, java.io.InputStream clis, int clPort) throws Throwable {
        client = new Socket(clHost, clPort);
        clis = client.getInputStream();
        clos = client.getOutputStream();
        client.setOOBInline(true);
        if (client.getOOBInline() != true) {
        }
    }
}

