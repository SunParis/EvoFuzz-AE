import java.text.BreakIterator;

public class TplClass5245 {

    private static final void method() throws Throwable {
        BreakIterator iterator = BreakIterator.getCharacterInstance();
        iterator.setText("\uDB40\uDFFF");
        int boundary = iterator.next();
    }
}

