import java.nio.charset.Charset;
import java.util.Iterator;

public class TplClass7416 {

    private static final void method(java.util.Iterator charsetIterator) throws Throwable {
        String charsetName = (String) charsetIterator.next();
        Charset charset = Charset.forName(charsetName);
        if (!charset.name().equals(charsetName)) {
        }
    }
}

