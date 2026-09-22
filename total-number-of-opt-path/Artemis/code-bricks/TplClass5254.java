import java.util.List;
import java.util.Locale;
import java.text.BreakIterator;
import java.util.ArrayList;

public class TplClass5254 {

    private static final void method(boolean err, java.util.List<java.lang.String> data, java.util.Locale locale) throws Throwable {
        List<BreakIterator> breakIterators = new ArrayList<>();
        breakIterators.add(BreakIterator.getCharacterInstance(locale));
        breakIterators.add(BreakIterator.getLineInstance(locale));
        breakIterators.add(BreakIterator.getSentenceInstance(locale));
        breakIterators.add(BreakIterator.getWordInstance(locale));
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

