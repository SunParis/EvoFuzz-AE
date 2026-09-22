import java.nio.charset.Charset;

public class TplClass5110 {

    private static final void method(java.lang.String start, java.nio.charset.Charset charset, java.lang.String end, java.lang.String enc, byte[] bytes) throws Throwable {
        if (enc.equals("UTF-16")) {
            if (!(new String(bytes, 0, 6, charset).equals(start)))
                ;
        } else {
            if (!(new String(bytes, 0, 2, charset).equals(start)))
                ;
            if (!(new String(bytes, 2, bytes.length - 2, charset).equals(end)))
                ;
        }
    }
}

