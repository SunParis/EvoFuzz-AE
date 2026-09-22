import java.nio.charset.CharsetEncoder;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

public class TplClass7288 {

    private static final void method(java.lang.String s, java.nio.charset.CharsetEncoder e, boolean reuseEncoder) throws Throwable {
        if (reuseEncoder) {
            // I'm turning japanese. Yes I'm turning japanese.  Yes I think so!
            e.encode(CharBuffer.wrap(s), ByteBuffer.allocate(64), true);
            // Should put encoder back into ASCII mode
            e.reset();
        }
    }
}

