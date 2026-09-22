import java.nio.channels.SelectionKey;

public class TplClass6710 {

    private static final void method(java.nio.channels.SelectionKey key) throws Throwable {
        for (int i = 0; i < 50000; i++) {
            key.interestOps(0);
        }
    }
}

