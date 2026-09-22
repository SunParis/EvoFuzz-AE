import java.nio.channels.ClosedByInterruptException;
import java.nio.channels.DatagramChannel;
import java.net.DatagramPacket;

public class TplClass5024 {

    private static final void method(java.nio.channels.DatagramChannel dc) throws Throwable {
        try {
            dc.socket().receive(new DatagramPacket(new byte[100], 100));
        } catch (ClosedByInterruptException expected) {
            Thread.currentThread().interrupted();
        }
    }
}

