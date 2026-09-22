import java.nio.channels.NetworkChannel;
import java.nio.channels.AlreadyBoundException;
import java.net.InetSocketAddress;

public class TplClass5010 {

    private static final void method(java.nio.channels.NetworkChannel ch) throws Throwable {
        try {
            ch.bind(new InetSocketAddress(0));
        } catch (AlreadyBoundException x) {
        }
    }
}

