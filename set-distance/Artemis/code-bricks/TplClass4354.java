import java.nio.ShortBuffer;

public class TplClass4354 {

    private static final void method(short[] myShorts, java.nio.ShortBuffer shortBuf) throws Throwable {
        try {
            shortBuf.position(0);
            // should fail
            shortBuf.put(myShorts, 0, 33);
        } catch (IndexOutOfBoundsException ioobe) {
        }
    }
}

