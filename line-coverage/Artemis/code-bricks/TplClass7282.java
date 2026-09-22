import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.io.ByteArrayOutputStream;

public class TplClass7282 {

    private static final void method(java.nio.charset.Charset ascii, java.io.ByteArrayOutputStream bos) throws Throwable {
        if (!ascii.decode(ByteBuffer.wrap(bos.toByteArray())).toString().equals("hi"))
            ;
    }
}

