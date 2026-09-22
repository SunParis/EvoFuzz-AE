import java.nio.channels.DatagramChannel;
import java.nio.channels.ClosedChannelException;
import java.net.DatagramPacket;

public class TplClass5023 {

    private static final void method(java.nio.channels.DatagramChannel dc) throws Throwable {
        try {
            dc.socket().receive(new DatagramPacket(new byte[100], 100));
        } catch (ClosedChannelException expected) {
        }
    }
}

