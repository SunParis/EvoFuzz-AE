import java.text.BreakIterator;

public class TplClass5255 {

    private static final void method(java.lang.String str, java.text.BreakIterator bi) throws Throwable {
        bi.setText(str);
        bi.first();
        while (bi.next() != BreakIterator.DONE) {
        }
        bi.last();
        while (bi.previous() != BreakIterator.DONE) {
        }
    }
}

