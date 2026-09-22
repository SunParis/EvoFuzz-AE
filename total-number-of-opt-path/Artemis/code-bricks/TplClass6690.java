import java.nio.channels.Selector;
import java.io.IOException;

public class TplClass6690 {

    private static final void method(boolean closed, java.nio.channels.Selector selector) throws Throwable {
        try {
            selector.close();
            closed = true;
        } catch (IOException e) {
        }
    }
}

