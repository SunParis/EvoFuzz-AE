import java.nio.ByteBuffer;
import java.nio.channels.Pipe.SinkChannel;

public class TplClass4956 {

    private static final void method(int totalWritten, java.nio.ByteBuffer outgoingdata, java.nio.channels.Pipe.SinkChannel sink) throws Throwable {
        while (totalWritten < 30) {
            int written = sink.write(outgoingdata);
            if (written < 0)
                ;
            totalWritten += written;
        }
    }
}

