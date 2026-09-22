import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.net.PortUnreachableException;

public class TplClass6565 {

    private static final void method(java.nio.ByteBuffer bb, boolean gotPUE, java.nio.channels.DatagramChannel dc1, boolean testSend) throws Throwable {
        try {
            if (testSend) {
                bb.rewind();
                dc1.write(bb);
            } else {
                bb.clear();
                dc1.receive(bb);
            }
        } catch (PortUnreachableException pue) {
            gotPUE = true;
        }
    }
}

