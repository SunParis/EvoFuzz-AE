import java.nio.channels.AsynchronousSocketChannel;

public class TplClass5681 {

    private static final void method(java.nio.channels.AsynchronousSocketChannel[] channels) throws Throwable {
        // clean-up
        for (AsynchronousSocketChannel ch : channels) ch.close();
    }
}

