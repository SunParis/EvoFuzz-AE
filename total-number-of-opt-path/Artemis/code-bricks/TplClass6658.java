import java.nio.channels.SelectionKey;

public class TplClass6658 {

    private static final void method(boolean done, java.nio.channels.SelectionKey key) throws Throwable {
        if (key.isReadable()) {
            done = true;
        }
    }
}

