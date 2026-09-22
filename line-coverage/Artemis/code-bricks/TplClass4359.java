import java.nio.ShortBuffer;

public class TplClass4359 {

    private static final void method(short[] myShorts, java.nio.ShortBuffer shortBuf) throws Throwable {
        shortBuf.position(16);
        // should fail
        shortBuf.put(myShorts, 0, 17);
    }
}

