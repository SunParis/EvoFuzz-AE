import java.util.Set;
import java.util.Iterator;
import java.util.Map.Entry;
import java.text.AttributedCharacterIterator;
import java.util.Map;

public class TplClass6879 {

    private static final void method(java.text.AttributedCharacterIterator iterator, char c) throws Throwable {
        if (iterator.getIndex() == iterator.getBeginIndex() || iterator.getIndex() == iterator.getRunStart()) {
            Map attributes = iterator.getAttributes();
            Set entries = attributes.entrySet();
            Iterator attributeIterator = entries.iterator();
            while (attributeIterator.hasNext()) {
                Map.Entry entry = (Map.Entry) attributeIterator.next();
            }
        }
    }
}

