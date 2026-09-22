import java.util.Map;
import java.util.Locale;

public class TplClass7448 {

    private static final void method(int conflicts, java.util.Locale[] locales, int min, int max, java.util.Map map) throws Throwable {
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
    }
}

