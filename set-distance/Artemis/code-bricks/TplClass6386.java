import java.nio.ByteBuffer;

public class TplClass6386 {

    private static final void method(java.nio.ByteBuffer bb, byte expect) throws Throwable {
        while (bb.hasRemaining()) {
            if (bb.get() != expect)
                ;
            expect++;
        }
    }
}

