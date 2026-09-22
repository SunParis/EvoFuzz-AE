import java.nio.channels.Pipe.SinkChannel;
import java.nio.channels.Pipe.SourceChannel;
import java.nio.channels.Selector;
import java.nio.channels.Pipe;
import java.nio.channels.SelectionKey;

public class TplClass6636 {

    private static final void method(java.nio.channels.Selector sel, int PIPES_COUNT, java.nio.channels.Pipe[] pipes) throws Throwable {
        for (int i = 0; i < PIPES_COUNT; i++) {
            pipes[i] = Pipe.open();
            Pipe.SourceChannel sc = pipes[i].source();
            sc.configureBlocking(false);
            sc.register(sel, SelectionKey.OP_READ);
            Pipe.SinkChannel sc2 = pipes[i].sink();
            sc2.configureBlocking(false);
            sc2.register(sel, SelectionKey.OP_WRITE);
        }
    }
}

