import java.nio.channels.AsynchronousSocketChannel;

public class TplClass5803 {

    private static final void method(java.nio.channels.AsynchronousSocketChannel ch2, java.nio.channels.AsynchronousSocketChannel ch1) throws Throwable {
        // channels should be closed
        if (ch1.isOpen() || ch2.isOpen())
            ;
    }
}

