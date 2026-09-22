import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class TplClass5648 {

    private static final void method(java.util.concurrent.Future<java.nio.channels.AsynchronousSocketChannel> res) throws Throwable {
        res.get(3, TimeUnit.SECONDS);
    }
}

