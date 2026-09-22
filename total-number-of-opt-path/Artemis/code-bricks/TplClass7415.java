import java.nio.charset.Charset;
import java.util.Iterator;

public class TplClass7415 {

    private static final void method() throws Throwable {
        Iterator charsetIterator = Charset.availableCharsets().keySet().iterator();
        while (charsetIterator.hasNext()) {
            String charsetName = (String) charsetIterator.next();
            Charset charset = Charset.forName(charsetName);
            if (!charset.name().equals(charsetName)) {
            }
        }
    }
}

