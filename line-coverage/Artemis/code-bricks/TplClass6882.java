import java.text.AttributedString;
import java.text.AttributedCharacterIterator;
import java.util.Hashtable;
import java.awt.font.TextAttribute;

public class TplClass6882 {

    private static final void method() throws Throwable {
        // Create a new AttributedString with one attribute.
        Hashtable attributes = new Hashtable();
        attributes.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);
        AttributedString origString = new AttributedString("Hello world.", attributes);
        // Create an iterator over part of the AttributedString.
        AttributedCharacterIterator iter = origString.getIterator(null, 4, 6);
        // This will throw IllegalArgumentException.
        AttributedString newString = new AttributedString(iter);
    }
}

