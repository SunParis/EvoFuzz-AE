import java.io.InputStream;
import java.net.Socket;
import java.net.InetAddress;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.io.InterruptedIOException;

public class TplClass6727 {

    private static final void method() throws Throwable {
        InetAddress sin = null;
        Socket soc = null, soc1 = null;
        InputStream is = null;
        OutputStream os = null;
        ServerSocket srv = null;
        int port = 0;
        int tout = 1000;
        sin = InetAddress.getLocalHost();
        srv = new ServerSocket(port);
        port = srv.getLocalPort();
        soc = new Socket(sin, port);
        soc1 = srv.accept();
        soc.setSoTimeout(tout);
        try {
            is = soc.getInputStream();
            os = soc1.getOutputStream();
            is.read();
        } catch (InterruptedIOException e) {
        } finally {
            soc.close();
            soc1.close();
            srv.close();
        }
    }
}

