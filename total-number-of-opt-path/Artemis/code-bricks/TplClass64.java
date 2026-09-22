import java.util.Arrays;
import java.nio.ByteBuffer;
import java.nio.charset.CharsetEncoder;
import java.nio.CharBuffer;

public class TplClass64 {

    private static final void method(byte[] b, byte[] bt, java.nio.charset.CharsetEncoder enc, java.nio.ByteBuffer bbt, java.nio.CharBuffer ba) throws Throwable {
        if (!enc.encode(ba, bbt, true).isUnderflow() || !Arrays.equals(b, bt)) {
        }
    }
}

