import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;

public class TplClass7418 {

    private static final void method(int i, java.lang.String[] illegalNames) throws Throwable {
        try {
            Charset.forName(illegalNames[i]);
        } catch (IllegalCharsetNameException x) {
            // expected
        }
    }
}

