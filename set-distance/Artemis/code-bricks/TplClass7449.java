import java.util.Map;
import java.util.Locale;

public class TplClass7449 {

    private static final void method(java.util.Locale loc, int conflicts, java.util.Map map, java.lang.Integer key) throws Throwable {
        if (map.containsKey(key)) {
            conflicts++;
        } else {
            map.put(key, loc);
        }
    }
}

