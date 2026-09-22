import java.nio.channels.Selector;
import java.nio.channels.SelectionKey;
import java.nio.channels.spi.SelectorProvider;
import java.nio.channels.Pipe.SinkChannel;
import java.nio.ByteBuffer;
import java.util.Random;
import java.nio.channels.Pipe;
import java.nio.channels.Pipe.SourceChannel;

public class TplClass4944 {

    private static final void method(java.util.Random generator) throws Throwable {
        SelectorProvider sp = SelectorProvider.provider();
        Selector selector = Selector.open();
        Pipe p = sp.openPipe();
        Pipe.SinkChannel sink = p.sink();
        Pipe.SourceChannel source = p.source();
        source.configureBlocking(false);
        sink.configureBlocking(false);
        SelectionKey readkey = source.register(selector, SelectionKey.OP_READ);
        SelectionKey writekey = sink.register(selector, SelectionKey.OP_WRITE);
        ByteBuffer outgoingdata = ByteBuffer.allocateDirect(10);
        byte[] someBytes = new byte[10];
        generator.nextBytes(someBytes);
        outgoingdata.put(someBytes);
        outgoingdata.flip();
        int totalWritten = 0;
        while (totalWritten < 10) {
            int written = sink.write(outgoingdata);
            if (written < 0)
                ;
            totalWritten += written;
        }
        if (selector.select(1000) == 0) {
        }
        ByteBuffer incomingdata = ByteBuffer.allocateDirect(10);
        int totalRead = 0;
        do {
            int bytesRead = source.read(incomingdata);
            if (bytesRead > 0)
                totalRead += bytesRead;
        } while (totalRead < 10);
        sink.close();
        source.close();
        selector.close();
        for (int i = 0; i < 10; i++) if (outgoingdata.get(i) != incomingdata.get(i))
            ;
    }
}

