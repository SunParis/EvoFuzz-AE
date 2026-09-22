import java.nio.charset.Charset;

public class TplClass5121 {

    private static final void method(java.lang.String start, java.nio.charset.Charset charset, java.lang.String end, byte[] bytes) throws Throwable {
        if (!(new String(bytes, 0, 2, charset).equals(start)))
            ;
        if (!(new String(bytes, 2, bytes.length - 2, charset).equals(end)))
            ;
    }
}

