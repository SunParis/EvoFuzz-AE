import java.nio.channels.DatagramChannel;
import java.net.StandardProtocolFamily;

public class TplClass6531 {

    private static final void method() throws Throwable {
        try (DatagramChannel dc = DatagramChannel.open()) {
            dc.bind(null);
        }
        try (DatagramChannel dc = DatagramChannel.open(StandardProtocolFamily.INET)) {
            dc.bind(null);
        }
        try (DatagramChannel dc = DatagramChannel.open(StandardProtocolFamily.INET6)) {
            dc.bind(null);
        } catch (UnsupportedOperationException uoe) {
            // IPv6 not available
        }
    }
}

