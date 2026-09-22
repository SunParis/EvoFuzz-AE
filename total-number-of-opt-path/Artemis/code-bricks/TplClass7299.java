import java.nio.charset.Charset;
import java.nio.ByteBuffer;
import java.util.Random;

public class TplClass7299 {

    private static final void method(java.nio.charset.Charset cs, java.util.Random rnd) throws Throwable {
        if (rnd.nextBoolean()) {
            cs.encode("hi mom");
        } else {
            cs.decode(ByteBuffer.wrap(new byte[] { (byte) 'x', (byte) 'y', (byte) 'z', (byte) 'z', (byte) 'y' }));
        }
    }
}

