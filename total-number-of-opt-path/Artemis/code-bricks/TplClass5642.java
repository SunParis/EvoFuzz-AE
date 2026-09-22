import java.nio.channels.AsynchronousChannelGroup;

public class TplClass5642 {

    private static final void method() throws Throwable {
        try {
            AsynchronousChannelGroup.withThreadPool(null);
        } catch (NullPointerException e) {
        }
    }
}

