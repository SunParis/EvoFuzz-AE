import java.nio.channels.ClosedByInterruptException;
import java.nio.channels.SocketChannel;

public class TplClass5022 {

    private static final void method(java.nio.channels.SocketChannel sc) throws Throwable {
        try {
            sc.socket().getInputStream().read(new byte[100]);
        } catch (ClosedByInterruptException expected) {
            Thread.currentThread().interrupted();
        }
    }
}

