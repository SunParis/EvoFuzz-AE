import java.nio.charset.Charset;
import java.util.Iterator;

public class TplClass7413 {

    private static final void method(java.util.Iterator charsetIterator) throws Throwable {
        while (charsetIterator.hasNext()) {
            String charsetName = (String) charsetIterator.next();
            Charset charset = Charset.forName(charsetName);
            if (!charset.name().equals(charsetName)) {
            }
        }
    }
}

