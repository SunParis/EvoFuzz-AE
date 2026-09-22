import java.nio.channels.Selector;

public class TplClass6645 {

    private static final void method() throws Throwable {
        Selector selector = Selector.open();
        try {
            for (int i = 0; i < 10000; i++) {
                selector.wakeup();
            }
        } finally {
            selector.close();
        }
    }
}

