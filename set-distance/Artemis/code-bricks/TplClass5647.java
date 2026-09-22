import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.nio.channels.ShutdownChannelGroupException;

public class TplClass5647 {

    private static final void method(java.util.concurrent.ExecutionException x) throws Throwable {
        Throwable cause = x.getCause();
        if (!(cause instanceof IOException))
            ;
        cause = cause.getCause();
        if (!(cause instanceof ShutdownChannelGroupException))
            ;
    }
}

