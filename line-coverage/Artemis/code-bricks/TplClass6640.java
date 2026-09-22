import java.nio.channels.Pipe.SinkChannel;
import java.nio.channels.Pipe.SourceChannel;
import java.nio.channels.Selector;
import java.nio.channels.Pipe;
import java.nio.channels.SelectionKey;

public class TplClass6640 {

    private static final void method(int i, java.nio.channels.Selector sel, java.nio.channels.Pipe[] pipes) throws Throwable {
        pipes[i] = Pipe.open();
        Pipe.SourceChannel sc = pipes[i].source();
        sc.configureBlocking(false);
        sc.register(sel, SelectionKey.OP_READ);
        Pipe.SinkChannel sc2 = pipes[i].sink();
        sc2.configureBlocking(false);
        sc2.register(sel, SelectionKey.OP_WRITE);
    }
}

