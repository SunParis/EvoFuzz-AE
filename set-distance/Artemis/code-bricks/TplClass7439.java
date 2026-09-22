import java.util.ArrayList;
import java.util.List;
import java.util.Locale.LanguageRange;

public class TplClass7439 {

    private static final void method(boolean err, java.util.List<java.util.Locale.LanguageRange> got, java.util.ArrayList<java.util.Locale.LanguageRange> expected) throws Throwable {
        if (!expected.equals(got)) {
            err = true;
            for (LanguageRange lr : expected) {
            }
            for (LanguageRange lr : got) {
            }
        }
    }
}

