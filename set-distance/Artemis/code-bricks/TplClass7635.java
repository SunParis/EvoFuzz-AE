import java.nio.ByteBuffer;

public class TplClass7635 {

    private static final void method() throws Throwable {
        for (int i = 0; i < 1024; i++) {
            ByteBuffer bb = ByteBuffer.allocateDirect(1024);
            // printByteBuffer(bb);
            for (bb.position(0); bb.position() < bb.limit(); ) {
                if ((bb.get() & 0xff) != 0)
                    ;
            }
        }
    }
}

