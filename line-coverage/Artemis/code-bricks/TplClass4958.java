import java.nio.ByteBuffer;
import java.nio.channels.Pipe.SourceChannel;
import java.util.Random;
import java.nio.channels.Pipe.SinkChannel;
import java.nio.channels.Pipe;

public class TplClass4958 {

    private static final void method(java.util.Random generator) throws Throwable {
        Pipe p = Pipe.open();
        Pipe.SinkChannel sink = p.sink();
        Pipe.SourceChannel source = p.source();
        sink.configureBlocking(false);
        ByteBuffer outgoingdata = ByteBuffer.allocateDirect(30);
        byte[] someBytes = new byte[30];
        generator.nextBytes(someBytes);
        outgoingdata.put(someBytes);
        outgoingdata.flip();
        int totalWritten = 0;
        while (totalWritten < 30) {
            int written = sink.write(outgoingdata);
            if (written < 0)
                ;
            totalWritten += written;
        }
        ByteBuffer[] bufs = new ByteBuffer[3];
        for (int i = 0; i < 3; i++) bufs[i] = ByteBuffer.allocateDirect(10);
        long numBytesRead = source.read(bufs);
        if (numBytesRead < 30)
            ;
        sink.close();
        source.close();
    }
}

