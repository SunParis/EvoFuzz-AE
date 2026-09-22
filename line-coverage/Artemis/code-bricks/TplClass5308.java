import java.net.SocketTimeoutException;
import java.io.InterruptedIOException;
import java.net.DatagramSocket;
import java.net.DatagramPacket;

public class TplClass5308 {

    private static final void method(java.net.DatagramSocket dg) throws Throwable {
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

