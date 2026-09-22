import java.util.concurrent.Future;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.TimeUnit;

public class TplClass5637 {

    private static final void method(java.util.concurrent.Future<java.nio.channels.AsynchronousSocketChannel> res) throws Throwable {
        try {
            res.get(3, TimeUnit.SECONDS);
        } catch (TimeoutException x) {
        }
    }
}

