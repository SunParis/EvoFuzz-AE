import java.util.zip.CRC32;
import java.nio.ByteBuffer;

public class TplClass44 {

    private static final void method(int start, int length, java.util.zip.CRC32 crc3, byte[] b) throws Throwable {
        ByteBuffer buf = ByteBuffer.allocateDirect(length);
        buf.put(b, start, length);
        buf.flip();
        crc3.update(buf);
    }
}

