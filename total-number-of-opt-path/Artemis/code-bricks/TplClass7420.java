import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;

public class TplClass7420 {

    private static final void method() throws Throwable {
        String[] illegalNames = { ".", "_", ":", "-", ".name", "_name", ":name", "-name", "name*name", "name?name" };
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

