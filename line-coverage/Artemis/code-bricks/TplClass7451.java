import java.util.Map;
import java.util.Locale;
import java.util.HashMap;

public class TplClass7451 {

    private static final void method() throws Throwable {
        Locale[] locales = Locale.getAvailableLocales();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        Map map = new HashMap(locales.length);
        int conflicts = 0;
        for (int i = 0; i < locales.length; i++) {
            Locale loc = locales[i];
            int hc = loc.hashCode();
            min = Math.min(hc, min);
            max = Math.max(hc, max);
            Integer key = new Integer(hc);
            if (map.containsKey(key)) {
                conflicts++;
            } else {
                map.put(key, loc);
            }
        }
        if (conflicts >= (locales.length / 10)) {
        }
    }
}

