import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class TplClass6567 {

    private static final void method(java.nio.ByteBuffer bb, int outstanding, java.nio.channels.DatagramChannel dc1) throws Throwable {
        bb.rewind();
        dc1.write(bb);
        outstanding++;
    }
}

