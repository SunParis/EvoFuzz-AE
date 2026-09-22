import java.util.List;
import java.nio.channels.AsynchronousSocketChannel;

public class TplClass5671 {

    private static final void method(java.util.List<java.nio.channels.AsynchronousSocketChannel> accepted) throws Throwable {
        for (AsynchronousSocketChannel ch : accepted) {
            ch.close();
        }
    }
}

