import java.nio.BufferOverflowException;
import java.nio.ShortBuffer;

public class TplClass4353 {

    private static final void method(short[] myShorts, java.nio.ShortBuffer shortBuf) throws Throwable {
        try {
            // should fail
            shortBuf.put(myShorts, 0, 1);
        } catch (BufferOverflowException boe) {
        }
    }
}

