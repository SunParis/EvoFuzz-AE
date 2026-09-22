import java.net.DatagramPacket;
import java.net.InetAddress;

public class TplClass5283 {

    private static final void method(byte[] buf, java.net.InetAddress address, int offset, int port, int length, java.net.DatagramPacket packet) throws Throwable {
        if (packet.getData() != buf || packet.getOffset() != offset || packet.getLength() != length || packet.getAddress() != address || packet.getPort() != port) {
        }
    }
}

