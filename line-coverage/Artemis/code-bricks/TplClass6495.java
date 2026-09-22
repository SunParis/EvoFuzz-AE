import java.nio.ByteBuffer;
import java.io.ByteArrayOutputStream;

public class TplClass6495 {

    private static final void method(java.nio.ByteBuffer bb, int DATA_LEN, java.io.ByteArrayOutputStream baos) throws Throwable {
        for (int i = 0; i < DATA_LEN; i++) {
            bb.putDouble(0, Math.random());
            baos.write(bb.array(), 0, 8);
        }
    }
}

