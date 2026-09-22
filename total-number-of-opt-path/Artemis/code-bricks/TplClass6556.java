import java.net.InetSocketAddress;
import java.nio.channels.DatagramChannel;

public class TplClass6556 {

    private static final void method(java.nio.channels.DatagramChannel dc) throws Throwable {
        dc = DatagramChannel.open().bind(new InetSocketAddress(0));
    }
}

