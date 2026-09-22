import java.nio.channels.Selector;
import java.io.IOException;

public class TplClass6689 {

    private static final void method(boolean awakened, java.nio.channels.Selector selector) throws Throwable {
        try {
            selector.select();
            awakened = true;
        } catch (IOException e) {
        }
    }
}

