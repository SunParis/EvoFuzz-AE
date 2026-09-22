import java.net.Socket;
import java.io.InputStream;
import java.io.OutputStream;

public class TplClass6809 {

    private static final void method(java.io.OutputStream clos, java.lang.String clHost, java.net.Socket client, boolean isClient, java.io.InputStream clis, int clPort) throws Throwable {
        if (isClient) {
            client = new Socket(clHost, clPort);
            clis = client.getInputStream();
            clos = client.getOutputStream();
            client.setOOBInline(true);
            if (client.getOOBInline() != true) {
            }
        }
    }
}

