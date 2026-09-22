import java.util.Map;

public class TplClass7592 {

    private static final void method(java.util.Map[] m) throws Throwable {
        for (int i = 0; i < m.length; i++) {
            m[i].put("bananas", null);
            if (!m[i].keySet().remove("bananas"))
                ;
        }
    }
}

