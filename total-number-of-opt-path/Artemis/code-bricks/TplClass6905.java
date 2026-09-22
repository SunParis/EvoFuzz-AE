import java.text.Bidi;
import java.text.AttributedString;
import java.awt.font.TextAttribute;

public class TplClass6905 {

    private static final void method() throws Throwable {
        String target = "BACK WARDS";
        String str = "If this text is >" + target + "< the test passed.";
        int length = str.length();
        int start = str.indexOf(target);
        int limit = start + target.length();
        AttributedString astr = new AttributedString(str);
        astr.addAttribute(TextAttribute.RUN_DIRECTION, TextAttribute.RUN_DIRECTION_RTL);
        astr.addAttribute(TextAttribute.BIDI_EMBEDDING, new Integer(-3), start, limit);
        Bidi bidi = new Bidi(astr.getIterator());
        for (int i = 0; i < bidi.getRunCount(); ++i) {
        }
        if (bidi.getRunCount() != 6) {
        } else {
        }
    }
}

