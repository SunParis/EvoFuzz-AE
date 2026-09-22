import java.nio.channels.Channel;

public class TplClass5441 {

    private static final void method(java.nio.channels.Channel c) throws Throwable {
        try {
            c = System.inheritedChannel();
        } catch (SecurityException se) {
            // ignore
        }
    }
}

