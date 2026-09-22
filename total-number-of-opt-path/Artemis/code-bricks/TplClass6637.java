import java.nio.channels.Pipe.SinkChannel;
import java.nio.channels.Pipe.SourceChannel;
import java.nio.channels.Selector;
import java.nio.ByteBuffer;

public class TplClass6637 {

    private static final void method(int BUF_SIZE, java.nio.channels.Pipe.SourceChannel source, java.nio.channels.Selector sel, int LOOPS, java.nio.channels.Pipe.SinkChannel sink) throws Throwable {
        for (int i = 0; i < LOOPS; i++) {
            sink.write(ByteBuffer.allocate(BUF_SIZE));
            int x = sel.selectNow();
            sel.selectedKeys().clear();
            source.read(ByteBuffer.allocate(BUF_SIZE));
        }
    }
}

