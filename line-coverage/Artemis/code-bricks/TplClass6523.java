import java.nio.channels.DatagramChannel;
import java.nio.channels.Selector;
import java.net.PortUnreachableException;
import java.nio.ByteBuffer;

public class TplClass6523 {

    private static final void method(java.nio.channels.Selector sel, int n, java.nio.channels.DatagramChannel dc) throws Throwable {
        sel.selectedKeys().clear();
        try {
            n = dc.read(ByteBuffer.allocate(100));
        } catch (PortUnreachableException pue) {
            // expected
        }
    }
}

