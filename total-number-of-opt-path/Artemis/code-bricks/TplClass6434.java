import java.nio.channels.ServerSocketChannel;

public class TplClass6434 {

    private static final void method(java.nio.channels.ServerSocketChannel sc) throws Throwable {
        try {
            sc.socket().setReceiveBufferSize(-1);
        } catch (IllegalArgumentException iae) {
            // correct behavior
        }
    }
}

