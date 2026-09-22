import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class TplClass6562 {

    private static final void method(java.nio.ByteBuffer bb, java.nio.channels.DatagramChannel dc1, boolean testSend) throws Throwable {
        if (testSend) {
            bb.rewind();
            dc1.write(bb);
        } else {
            bb.clear();
            dc1.receive(bb);
        }
    }
}

