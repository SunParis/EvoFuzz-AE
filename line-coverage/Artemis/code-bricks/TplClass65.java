import java.nio.charset.CharsetDecoder;
import java.util.Arrays;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

public class TplClass65 {

    private static final void method(java.nio.ByteBuffer bb, char[] a, java.nio.charset.CharsetDecoder dec, char[] at, java.nio.CharBuffer bat) throws Throwable {
        if (!dec.decode(bb, bat, true).isUnderflow() || !Arrays.equals(a, at)) {
        }
    }
}

