import java.nio.ByteBuffer;

public class TplClass7636 {

    private static final void method(java.nio.ByteBuffer bb) throws Throwable {
        // printByteBuffer(bb);
        for (bb.position(0); bb.position() < bb.limit(); ) {
            if ((bb.get() & 0xff) != 0)
                ;
        }
    }
}

