import java.nio.channels.Selector;

public class TplClass6686 {

    private static final void method(boolean awakened, java.nio.channels.Selector selector) throws Throwable {
        if (!awakened)
            selector.wakeup();
    }
}

