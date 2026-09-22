import java.io.Closeable;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.ServerSocket;
import java.net.DatagramSocket;

public class TplClass4761 {

    private static final void method() throws Throwable {
        Socket s = new Socket();
        ServerSocket ss = new ServerSocket();
        DatagramSocket ds = new DatagramSocket((SocketAddress) null);
        if (!(s instanceof Closeable))
            ;
        if (!(ss instanceof Closeable))
            ;
        if (!(ds instanceof Closeable))
            ;
        s.close();
        ss.close();
        ds.close();
    }
}

