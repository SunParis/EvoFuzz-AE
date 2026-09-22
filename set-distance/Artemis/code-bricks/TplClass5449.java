import java.io.IOException;
import java.nio.channels.Channel;
import java.nio.channels.spi.SelectorProvider;

public class TplClass5449 {

    private static final void method(Channel c1, Channel c2) throws Throwable {
        try {
            c1 = SelectorProvider.provider().inheritedChannel();
            c2 = System.inheritedChannel();
        } catch (IOException ioe) {
        }
        if (c1 != null || c2 != null) {
        }
    }
}

