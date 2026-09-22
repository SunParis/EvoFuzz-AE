import java.util.List;
import java.util.Locale;
import java.text.BreakIterator;

public class TplClass5247 {

    private static final void method(java.util.List<java.text.BreakIterator> breakIterators, boolean err, java.util.List<java.lang.String> data, java.util.Locale locale) throws Throwable {
        for (BreakIterator bi : breakIterators) {
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
}

