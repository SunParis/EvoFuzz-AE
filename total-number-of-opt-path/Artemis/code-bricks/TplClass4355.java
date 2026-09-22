import java.nio.BufferOverflowException;
import java.nio.ShortBuffer;

public class TplClass4355 {

    private static final void method(short[] myShorts, java.nio.ShortBuffer shortBuf) throws Throwable {
        try {
            shortBuf.position(16);
            // should fail
            shortBuf.put(myShorts, 0, 17);
        } catch (BufferOverflowException boe) {
        }
    }
}

