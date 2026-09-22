import java.util.ArrayList;
import java.util.List;
import java.util.Locale.LanguageRange;

public class TplClass7441 {

    private static final void method(int i, java.lang.String[][] mappings, boolean err) throws Throwable {
        List<LanguageRange> got = LanguageRange.parse(mappings[i][0]);
        ArrayList<LanguageRange> expected = new ArrayList<>();
        expected.add(new LanguageRange(mappings[i][0], 1.0));
        expected.add(new LanguageRange(mappings[i][1], 1.0));
        if (!expected.equals(got)) {
            err = true;
            for (LanguageRange lr : expected) {
            }
            for (LanguageRange lr : got) {
            }
        }
    }
}

