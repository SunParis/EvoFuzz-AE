import java.text.Bidi;
import java.text.AttributedString;
import java.awt.font.TextAttribute;

public class TplClass6903 {

    private static final void method() throws Throwable {
        String target = "BACK WARDS";
        String str = "If this text is >" + target + "< the test passed.";
        int start = str.indexOf(target);
        int limit = start + target.length();
        AttributedString astr = new AttributedString(str);
        astr.addAttribute(TextAttribute.BIDI_EMBEDDING, new Integer(-1), start, limit);
        Bidi bidi = new Bidi(astr.getIterator());
        for (int i = 0; i < bidi.getRunCount(); ++i) {
        }
        byte[] embs = new byte[str.length() + 3];
        for (int i = start + 1; i < limit + 1; ++i) {
            embs[i] = -1;
        }
        Bidi bidi2 = new Bidi(str.toCharArray(), 0, embs, 1, str.length(), Bidi.DIRECTION_DEFAULT_LEFT_TO_RIGHT);
        for (int i = 0; i < bidi2.getRunCount(); ++i) {
        }
        if (bidi.getRunCount() != 3 || bidi2.getRunCount() != 3) {
        } else {
        }
    }
}

