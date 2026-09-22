import java.nio.ByteBuffer;
import java.io.ByteArrayOutputStream;

public class TplClass6501 {

    private static final void method(java.nio.ByteBuffer bb, java.io.ByteArrayOutputStream baos) throws Throwable {
        bb.putDouble(0, Math.random());
        baos.write(bb.array(), 0, 8);
    }
}

