import java.nio.channels.Selector;
import java.nio.channels.spi.SelectorProvider;

public class TplClass6685 {

    private static final void method() throws Throwable {
        Selector s = SelectorProvider.provider().openSelector();
        s.selectNow();
    }
}

