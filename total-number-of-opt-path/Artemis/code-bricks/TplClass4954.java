import java.nio.ByteBuffer;
import java.nio.channels.spi.SelectorProvider;
import java.nio.channels.Pipe.SinkChannel;
import java.nio.channels.Pipe;
import java.nio.channels.Pipe.SourceChannel;

public class TplClass4954 {

    private static final void method() throws Throwable {
        SelectorProvider sp = SelectorProvider.provider();
        Pipe p = sp.openPipe();
        Pipe.SinkChannel sink = p.sink();
        Pipe.SourceChannel source = p.source();
        byte[] someBytes = new byte[0];
        ByteBuffer outgoingdata = ByteBuffer.wrap(someBytes);
        int totalWritten = 0;
        int written = sink.write(outgoingdata);
        if (written < 0)
            ;
        ByteBuffer incomingdata = ByteBuffer.allocateDirect(0);
        int read = source.read(incomingdata);
        if (read < 0)
            ;
        sink.close();
        source.close();
    }
}

