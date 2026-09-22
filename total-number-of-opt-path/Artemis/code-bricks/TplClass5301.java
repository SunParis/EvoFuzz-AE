import java.net.DatagramPacket;
import java.net.InetAddress;

public class TplClass5301 {

    private static final void method() throws Throwable {
        byte[] buf = new byte[128];
        int offset = 10;
        int length = 50;
        InetAddress address = InetAddress.getLocalHost();
        int port = 8080;
        DatagramPacket packet = new DatagramPacket(buf, offset, length, address, port);
        if (packet.getData() != buf || packet.getOffset() != offset || packet.getLength() != length || packet.getAddress() != address || packet.getPort() != port) {
        }
    }
}

