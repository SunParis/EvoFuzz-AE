import java.nio.ByteBuffer;
import java.util.Random;

public class TplClass5685 {

    private static final void method(java.util.Random rand, java.nio.ByteBuffer buf) throws Throwable {
        if (rand.nextBoolean()) {
            buf = ByteBuffer.allocateDirect(1);
        } else {
            buf = ByteBuffer.allocate(1);
        }
    }
}

