import java.nio.channels.spi.SelectorProvider;
import java.nio.channels.Pipe.SinkChannel;
import java.util.Random;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe.SourceChannel;
import java.nio.channels.Pipe;

public class TplClass4948 {

    private static final void method(java.util.Random generator) throws Throwable {
        SelectorProvider sp = SelectorProvider.provider();
        Pipe p = sp.openPipe();
        Pipe.SinkChannel sink = p.sink();
        Pipe.SourceChannel source = p.source();
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
        ByteBuffer incomingdata = ByteBuffer.allocateDirect(10);
        int totalRead = 0;
        do {
            int bytesRead = source.read(incomingdata);
            if (bytesRead > 0)
                totalRead += bytesRead;
        } while (totalRead < 10);
        for (int i = 0; i < 10; i++) if (outgoingdata.get(i) != incomingdata.get(i))
            ;
        sink.close();
        source.close();
    }
}

