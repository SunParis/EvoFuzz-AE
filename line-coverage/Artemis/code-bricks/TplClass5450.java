import java.nio.channels.Channel;
import java.nio.channels.spi.SelectorProvider;

public class TplClass5450 {

    private static final void method(java.nio.channels.Channel c1, java.nio.channels.Channel c2) throws Throwable {
        c1 = SelectorProvider.provider().inheritedChannel();
        c2 = System.inheritedChannel();
    }
}

