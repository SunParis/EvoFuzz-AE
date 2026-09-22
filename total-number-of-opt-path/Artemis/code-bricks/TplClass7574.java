import java.util.Set;
import java.util.Map;
import java.util.HashSet;
import java.util.TimeZone;

public class TplClass7574 {

    private static final void method(java.util.Map<java.lang.Integer, java.util.Set<java.lang.String>> tree, java.util.Set<java.lang.String> ids, java.lang.String id) throws Throwable {
        ids.add(id);
        TimeZone tz = TimeZone.getTimeZone(id);
        Integer offset = tz.getRawOffset();
        Set<String> s = tree.get(offset);
        if (s == null) {
            s = new HashSet<>();
            tree.put(offset, s);
        }
        s.add(id);
    }
}

