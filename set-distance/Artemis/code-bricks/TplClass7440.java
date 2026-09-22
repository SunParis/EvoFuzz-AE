import java.util.ArrayList;
import java.util.List;
import java.util.Locale.LanguageRange;

public class TplClass7440 {

    private static final void method() throws Throwable {
        boolean err = false;
        String[][] mappings = { { "ilw", "gal" }, { "meg", "cir" }, { "pcr", "adx" }, { "xia", "acn" }, { "yos", "zom" } };
        for (int i = 0; i < mappings.length; i++) {
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
        if (err) {
        }
    }
}

