import java.net.DatagramPacket;

public class TplClass5300 {

    private static final void method() throws Throwable {
        byte[] buf = new byte[128];
        int offset = 10;
        int length = 50;
        DatagramPacket packet = new DatagramPacket(buf, offset, length);
        if (packet.getData() != buf || packet.getOffset() != offset || packet.getLength() != length) {
        }
    }
}

