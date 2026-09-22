import java.nio.channels.AsynchronousChannelGroup;

public class TplClass5639 {

    private static final void method() throws Throwable {
        try {
            AsynchronousChannelGroup.withFixedThreadPool(1, null);
        } catch (NullPointerException x) {
        }
    }
}

