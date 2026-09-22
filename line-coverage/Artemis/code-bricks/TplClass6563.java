import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.net.PortUnreachableException;

public class TplClass6563 {

    private static final void method(java.nio.ByteBuffer bb, boolean gotPUE, java.nio.channels.DatagramChannel dc1) throws Throwable {
        /*
         * The next receive should not get another PUE
         */
        if (gotPUE) {
            try {
                dc1.receive(bb);
            } catch (PortUnreachableException pue) {
            }
        } else {
            // packets discarded. Okay
        }
    }
}

