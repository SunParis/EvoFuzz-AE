import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;

public class TplClass7421 {

    private static final void method(int i, java.lang.String[] illegalNames) throws Throwable {
        try {
            Charset.forName(illegalNames[i]);
        } catch (IllegalCharsetNameException x) {
            // expected
        }
        try {
            Charset.isSupported(illegalNames[i]);
        } catch (IllegalCharsetNameException x) {
            // expected
        }
    }
}

