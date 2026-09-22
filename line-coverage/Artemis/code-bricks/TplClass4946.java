import java.nio.channels.Pipe.SourceChannel;
import java.nio.ByteBuffer;

public class TplClass4946 {

    private static final void method(java.nio.ByteBuffer incomingdata, java.nio.channels.Pipe.SourceChannel source, int totalRead) throws Throwable {
        int bytesRead = source.read(incomingdata);
        if (bytesRead > 0)
            totalRead += bytesRead;
    }
}

