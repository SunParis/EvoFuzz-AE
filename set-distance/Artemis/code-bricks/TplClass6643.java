import java.nio.channels.Selector;

public class TplClass6643 {

    private static final void method(java.nio.channels.Selector selector) throws Throwable {
        for (int i = 0; i < 10000; i++) {
            selector.wakeup();
        }
    }
}

