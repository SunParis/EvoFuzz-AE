import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class TplClass7412 {

    private static final void method() throws Throwable {
        String s = "Hello, world!";
        ByteBuffer bb = ByteBuffer.allocate(100);
        bb.put(Charset.forName("ISO-8859-15").encode(s)).flip();
        String t = Charset.forName("UTF-8").decode(bb).toString();
        if (!t.equals(s))
            ;
    }
}

