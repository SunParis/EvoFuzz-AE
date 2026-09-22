import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class TplClass7495 {

    private static final void method(java.net.DatagramPacket p, java.net.DatagramSocket s) throws Throwable {
        for (int i = 0; i < 10; i++) {
            s.send(p);
            Thread.currentThread().sleep(1000);
        }
    }
}

