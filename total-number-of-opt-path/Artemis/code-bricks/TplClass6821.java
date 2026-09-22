import java.io.InputStream;
import java.net.Socket;
import java.net.ServerSocket;
import java.io.OutputStream;

public class TplClass6821 {

    private static final void method(java.net.Socket server, boolean isServer, java.io.OutputStream clos, java.lang.String clHost, java.io.OutputStream sos, java.net.ServerSocket listener, java.net.Socket client, boolean isClient, java.io.InputStream sis, java.io.InputStream clis, int clPort) throws Throwable {
        if (isClient) {
            client = new Socket(clHost, clPort);
            clis = client.getInputStream();
            clos = client.getOutputStream();
            client.setOOBInline(true);
            if (client.getOOBInline() != true) {
            }
        }
        if (isServer) {
            server = listener.accept();
            sis = server.getInputStream();
            sos = server.getOutputStream();
        }
        if (isClient) {
            clos.write("Hello".getBytes());
            client.sendUrgentData(100);
            clos.write("world".getBytes());
        }
        // read Hello world from server (during which oob byte must have been dropped)
        String s = "Helloworld";
        if (isServer) {
            for (int y = 0; y < s.length(); y++) {
                int c = sis.read();
                if (c != (int) s.charAt(y)) {
                }
            }
            // Do the same from server to client
            sos.write("Hello".getBytes());
            server.sendUrgentData(101);
            sos.write("World".getBytes());
        }
        if (isClient) {
            // read Hello world from client (during which oob byte must have been read)
            s = "Hello";
            for (int y = 0; y < s.length(); y++) {
                int c = clis.read();
                if (c != (int) s.charAt(y)) {
                }
            }
            if (clis.read() != 101) {
            }
            s = "World";
            for (int y = 0; y < s.length(); y++) {
                int c = clis.read();
                if (c != (int) s.charAt(y)) {
                }
            }
        }
    }
}

