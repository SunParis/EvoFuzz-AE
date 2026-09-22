import java.util.Locale;
import java.text.Collator;
import java.util.Arrays;
import java.util.List;

public class TplClass5921 {

    private static final void method() throws Throwable {
        List<Locale> avail = Arrays.asList(Collator.getAvailableLocales());
        if (!avail.contains(Locale.US)) {
        }
    }
}

