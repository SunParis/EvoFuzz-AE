import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.ByteBuffer;
import java.nio.charset.CoderResult;
import java.nio.charset.Charset;

public class TplClass5244 {

    private static final void method(java.lang.String csName, int expected, byte[] ba) throws Throwable {
        CoderResult cr = Charset.forName(csName).newDecoder().decode(ByteBuffer.wrap(ba), CharBuffer.allocate(4), true);
        if (cr.isUnmappable() && cr.length() != expected) {
        }
    }
}

