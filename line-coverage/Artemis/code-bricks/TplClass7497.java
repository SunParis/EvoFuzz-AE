import java.net.InetAddress;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class TplClass7497 {

    private static final void method(int port, java.net.DatagramSocket s) throws Throwable {
        byte[] b = "Hello".getBytes();
        DatagramPacket p = new DatagramPacket(b, b.length);
        p.setAddress(InetAddress.getLocalHost());
        p.setPort(port);
        for (int i = 0; i < 10; i++) {
            s.send(p);
            Thread.currentThread().sleep(1000);
        }
    }
}

