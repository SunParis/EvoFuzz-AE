import java.util.Map;
import java.util.Locale;

public class TplClass7452 {

    private static final void method(java.util.Locale[] locales, int min, int max, int conflicts, int i, java.util.Map map) throws Throwable {
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

