import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class TplClass6415 {

    private static final void method(java.nio.ByteBuffer buf, java.nio.channels.FileChannel fc, long iterations) throws Throwable {
        while (iterations < 50) {
            fc.write(buf);
            buf.rewind();
            iterations++;
        }
    }
}

