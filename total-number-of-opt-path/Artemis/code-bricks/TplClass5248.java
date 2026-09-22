import java.util.Locale;
import java.text.BreakIterator;
import java.util.List;

public class TplClass5248 {

    private static final void method(boolean err, java.util.List<java.lang.String> data, java.util.Locale locale, java.text.BreakIterator bi) throws Throwable {
        for (String str : data) {
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
}

