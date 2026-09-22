import java.nio.charset.CharsetEncoder;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

public class TplClass7289 {

    private static final void method(java.nio.charset.CharsetEncoder e, java.lang.String s) throws Throwable {
        // I'm turning japanese. Yes I'm turning japanese.  Yes I think so!
        e.encode(CharBuffer.wrap(s), ByteBuffer.allocate(64), true);
        // Should put encoder back into ASCII mode
        e.reset();
    }
}

