import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;

public class TplClass7417 {

    private static final void method(java.lang.String[] illegalNames) throws Throwable {
        for (int i = 0; i < illegalNames.length; i++) {
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
}

