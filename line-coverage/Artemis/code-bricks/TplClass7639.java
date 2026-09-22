import java.nio.ByteBuffer;

public class TplClass7639 {

    private static final void method() throws Throwable {
        ByteBuffer bb = ByteBuffer.allocateDirect(1024);
        // printByteBuffer(bb);
        for (bb.position(0); bb.position() < bb.limit(); ) {
            if ((bb.get() & 0xff) != 0)
                ;
        }
    }
}

