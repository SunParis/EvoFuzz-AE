import java.nio.ByteBuffer;
import java.nio.charset.CharsetEncoder;
import java.nio.CharBuffer;

public class TplClass61 {

    private static final void method(java.nio.ByteBuffer bb, boolean enc_res, java.nio.charset.CharsetEncoder enc, java.nio.CharBuffer ba) throws Throwable {
        for (int i = 0; i < 10000; i++) {
            ba.clear();
            bb.clear();
            enc_res = enc_res && enc.encode(ba, bb, true).isUnderflow();
        }
    }
}

