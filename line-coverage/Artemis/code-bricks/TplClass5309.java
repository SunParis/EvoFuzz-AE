import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.ServerSocket;
import java.net.SocketTimeoutException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.DatagramPacket;
import java.net.Socket;

public class TplClass5309 {

    private static final void method(int TIMEOUT) throws Throwable {
        InetAddress sin = InetAddress.getLocalHost();
        Socket soc = null, soc1 = null;
        InputStream is = null;
        ServerSocket srv = null;
        int port = 0;
        srv = new ServerSocket(0);
        port = srv.getLocalPort();
        soc = new Socket(sin, port);
        soc1 = srv.accept();
        soc.setSoTimeout(TIMEOUT);
        srv.setSoTimeout(TIMEOUT);
        try {
            is = soc.getInputStream();
            is.read();
        } catch (InterruptedIOException e) {
            try {
                if (!(e instanceof java.net.SocketTimeoutException))
                    ;
            } catch (NoClassDefFoundError e1) {
            }
        } finally {
            soc.close();
            soc1.close();
        }
        try {
            srv.accept();
        } catch (InterruptedIOException e) {
            try {
                if (!(e instanceof java.net.SocketTimeoutException))
                    ;
            } catch (NoClassDefFoundError e1) {
            }
        } finally {
            srv.close();
        }
        DatagramSocket dg = new DatagramSocket();
        dg.setSoTimeout(TIMEOUT);
        try {
            dg.receive(new DatagramPacket(new byte[64], 64));
        } catch (InterruptedIOException e) {
            try {
                if (!(e instanceof java.net.SocketTimeoutException))
                    ;
            } catch (NoClassDefFoundError e1) {
            }
        } finally {
            dg.close();
        }
    }
}

