import java.nio.charset.Charset;

public class TplClass5120 {

    private static final void method(java.lang.String start, java.nio.charset.Charset charset, java.lang.String end, byte[] bytes) throws Throwable {
        if (!(new String(bytes, 0, 4, charset).equals(start)))
            ;
        if (!(new String(bytes, 4, bytes.length - 4, charset).equals(end)))
            ;
    }
}

