import java.util.Locale;
import java.text.BreakIterator;
import java.util.List;

public class TplClass5252 {

    private static final void method(java.lang.String str, boolean err, java.util.List<java.lang.String> data, java.util.Locale locale, java.text.BreakIterator bi) throws Throwable {
        try {
            bi.setText(str);
            bi.first();
            while (bi.next() != BreakIterator.DONE) {
            }
            bi.last();
            while (bi.previous() != BreakIterator.DONE) {
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            err = true;
        }
    }
}

