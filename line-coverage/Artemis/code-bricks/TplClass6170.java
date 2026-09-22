import java.util.TreeMap;
import java.util.Map;

public class TplClass6170 {

    private static final void method() throws Throwable {
        Map map = new TreeMap();
        if (map.containsValue("gemutlichkeit"))
            ;
        if (map.containsValue(null))
            ;
        map.put("a", null);
        map.put("b", "gemutlichkeit");
        if (!map.containsValue("gemutlichkeit"))
            ;
        if (!map.containsValue(null))
            ;
    }
}

