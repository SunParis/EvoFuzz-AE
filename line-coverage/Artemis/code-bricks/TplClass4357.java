import java.nio.ShortBuffer;

public class TplClass4357 {

    private static final void method(short[] myShorts, java.nio.ShortBuffer shortBuf) throws Throwable {
        // should fail
        shortBuf.put(myShorts, 0, 1);
    }
}

