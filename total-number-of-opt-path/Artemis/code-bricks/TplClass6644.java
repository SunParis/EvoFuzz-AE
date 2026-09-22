import java.nio.channels.Selector;

public class TplClass6644 {

    private static final void method(java.nio.channels.Selector selector) throws Throwable {
        try {
            for (int i = 0; i < 10000; i++) {
                selector.wakeup();
            }
        } finally {
            selector.close();
        }
    }
}

