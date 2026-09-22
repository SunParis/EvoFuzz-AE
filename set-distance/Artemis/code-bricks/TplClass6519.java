import java.nio.channels.Selector;
import java.nio.channels.DatagramChannel;
import java.net.PortUnreachableException;
import java.nio.ByteBuffer;

public class TplClass6519 {

    private static final void method(java.nio.channels.Selector sel, int n, java.nio.channels.DatagramChannel dc) throws Throwable {
        if (n > 0) {
            sel.selectedKeys().clear();
            try {
                n = dc.read(ByteBuffer.allocate(100));
            } catch (PortUnreachableException pue) {
                // expected
            }
        }
    }
}

