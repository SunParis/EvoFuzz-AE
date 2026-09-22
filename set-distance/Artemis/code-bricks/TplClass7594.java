import java.util.TreeMap;
import java.util.Map;
import java.util.HashMap;

public class TplClass7594 {

    private static final void method() throws Throwable {
        Map[] m = { new HashMap(), new TreeMap() };
        for (int i = 0; i < m.length; i++) {
            m[i].put("bananas", null);
            if (!m[i].keySet().remove("bananas"))
                ;
        }
    }
}

