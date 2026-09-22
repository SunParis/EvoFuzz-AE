import java.nio.charset.CharsetDecoder;
import java.nio.ByteBuffer;
import java.nio.charset.CharsetEncoder;
import java.nio.CharBuffer;

public class TplClass82 {

    private static final void method(boolean is_underflow, java.nio.ByteBuffer bb, java.nio.charset.CharsetDecoder dec, java.nio.CharBuffer bat, java.nio.ByteBuffer bbt, java.nio.charset.CharsetEncoder enc, java.nio.CharBuffer ba) throws Throwable {
        ba.clear();
        bb.clear();
        bat.clear();
        bbt.clear();
        boolean enc_res = enc.encode(ba, bbt, true).isUnderflow();
        boolean dec_res = dec.decode(bb, bat, true).isUnderflow();
        is_underflow = is_underflow && enc_res && dec_res;
    }
}

