import java.nio.channels.NetworkChannel;
import java.nio.channels.ClosedChannelException;

public class TplClass5012 {

    private static final void method(java.nio.channels.NetworkChannel ch) throws Throwable {
        try {
            ch.getLocalAddress();
        } catch (ClosedChannelException e) {
        }
    }
}

