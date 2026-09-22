import java.nio.ShortBuffer;

public class TplClass4358 {

    private static final void method(short[] myShorts, java.nio.ShortBuffer shortBuf) throws Throwable {
        shortBuf.position(0);
        // should fail
        shortBuf.put(myShorts, 0, 33);
    }
}

