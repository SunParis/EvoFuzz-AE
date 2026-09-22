import java.io.PrintStream;
import java.nio.ByteBuffer;

public class TplClass6547 {

    private static final void method(java.nio.ByteBuffer bb, java.io.PrintStream log) throws Throwable {
        for (int i = 0; i < bb.limit(); i++) {
            byte element = bb.get();
            log.print(element);
        }
    }
}

