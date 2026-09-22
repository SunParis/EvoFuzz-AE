import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.net.PortUnreachableException;

public class TplClass6564 {

    private static final void method(java.nio.ByteBuffer bb, int outstanding, java.nio.channels.DatagramChannel dc1) throws Throwable {
        try {
            bb.rewind();
            dc1.write(bb);
            outstanding++;
        } catch (PortUnreachableException e) {
            /* PUE throw => assume none outstanding now */
            outstanding = 0;
        }
    }
}

