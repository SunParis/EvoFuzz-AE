import java.util.Set;
import java.util.Iterator;
import java.util.Map.Entry;
import java.text.AttributedCharacterIterator;
import java.util.Map;

public class TplClass6880 {

    private static final void method(java.text.AttributedCharacterIterator iterator) throws Throwable {
        Map attributes = iterator.getAttributes();
        Set entries = attributes.entrySet();
        Iterator attributeIterator = entries.iterator();
        while (attributeIterator.hasNext()) {
            Map.Entry entry = (Map.Entry) attributeIterator.next();
        }
    }
}

