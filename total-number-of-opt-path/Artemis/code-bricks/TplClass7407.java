import java.util.Map;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.Hashtable;
import java.util.Set;
import java.util.TreeMap;

public class TplClass7407 {

    private static final void method() throws Throwable {
        Map m = new HashMap();
        m.put(null, "");
        Map h = new Hashtable();
        h.put("", "");
        if (m.equals(h))
            ;
        Map m1 = new TreeMap();
        m1.put(new Integer(42), "The Answer");
        Map m2 = new TreeMap();
        m2.put("The Answer", new Integer(42));
        if (m1.equals(m2))
            ;
        Set s1 = new TreeSet();
        s1.add(new Integer(666));
        Set s2 = new TreeSet();
        s2.add("Great googly moogly!");
        if (s1.equals(s2))
            ;
    }
}

