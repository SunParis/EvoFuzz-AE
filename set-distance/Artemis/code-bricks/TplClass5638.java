import java.util.concurrent.Future;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.nio.channels.ShutdownChannelGroupException;

public class TplClass5638 {

    private static final void method(java.util.concurrent.Future<java.nio.channels.AsynchronousSocketChannel> res) throws Throwable {
        try {
            res.get();
        } catch (ExecutionException x) {
            Throwable cause = x.getCause();
            if (!(cause instanceof IOException))
                ;
            cause = cause.getCause();
            if (!(cause instanceof ShutdownChannelGroupException))
                ;
        }
    }
}

