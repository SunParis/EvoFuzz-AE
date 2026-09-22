import java.nio.ByteBuffer;
import java.io.ByteArrayOutputStream;

public class TplClass6500 {

    private static final void method(byte[] data, int DATA_LEN) throws Throwable {
        ByteBuffer bb = ByteBuffer.allocate(8);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        for (int i = 0; i < DATA_LEN; i++) {
            bb.putDouble(0, Math.random());
            baos.write(bb.array(), 0, 8);
        }
        data = baos.toByteArray();
    }
}

