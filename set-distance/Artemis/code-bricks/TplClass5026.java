import java.nio.channels.ClosedByInterruptException;
import java.nio.channels.ServerSocketChannel;

public class TplClass5026 {

    private static final void method(java.nio.channels.ServerSocketChannel ssc) throws Throwable {
        try {
            ssc.socket().accept();
        } catch (ClosedByInterruptException expected) {
            Thread.currentThread().interrupted();
        }
    }
}

