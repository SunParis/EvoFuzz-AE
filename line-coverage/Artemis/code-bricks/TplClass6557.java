import java.io.PrintStream;
import java.nio.ByteBuffer;

public class TplClass6557 {

    private static final void method(java.nio.ByteBuffer bb, java.lang.String s, java.io.PrintStream log) throws Throwable {
        log.println(s);
        bb.rewind();
        for (int i = 0; i < bb.limit(); i++) {
            byte element = bb.get();
            log.print(element);
        }
        log.println();
    }
}

