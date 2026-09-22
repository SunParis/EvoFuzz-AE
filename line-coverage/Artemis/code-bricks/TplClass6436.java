import java.nio.channels.ServerSocketChannel;

public class TplClass6436 {

    private static final void method() throws Throwable {
        ServerSocketChannel sc = ServerSocketChannel.open();
        try {
            sc.socket().setReceiveBufferSize(-1);
        } catch (IllegalArgumentException iae) {
            // correct behavior
        }
        try {
            sc.socket().setReceiveBufferSize(0);
        } catch (IllegalArgumentException iae) {
            // correct behavior
        }
        sc.close();
    }
}

