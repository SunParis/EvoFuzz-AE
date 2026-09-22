import java.nio.channels.Pipe.SinkChannel;
import java.nio.ByteBuffer;

public class TplClass4945 {

    private static final void method(int totalWritten, java.nio.ByteBuffer outgoingdata, java.nio.channels.Pipe.SinkChannel sink) throws Throwable {
        int written = sink.write(outgoingdata);
        if (written < 0)
            ;
        totalWritten += written;
    }
}

