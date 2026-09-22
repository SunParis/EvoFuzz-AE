import java.util.concurrent.TimeUnit;
import java.nio.channels.AsynchronousChannelGroup;

public class TplClass5643 {

    private static final void method(java.nio.channels.AsynchronousChannelGroup group) throws Throwable {
        boolean terminated = group.awaitTermination(20, TimeUnit.SECONDS);
        if (!terminated)
            ;
    }
}

