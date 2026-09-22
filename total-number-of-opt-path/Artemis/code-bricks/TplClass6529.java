import java.nio.channels.DatagramChannel;
import java.net.StandardProtocolFamily;

public class TplClass6529 {

    private static final void method() throws Throwable {
        try (DatagramChannel dc = DatagramChannel.open(StandardProtocolFamily.INET)) {
            dc.bind(null);
        }
    }
}

