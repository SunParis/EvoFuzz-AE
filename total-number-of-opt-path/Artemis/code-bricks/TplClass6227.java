import java.util.Map;

public class TplClass6227 {

    private static final void method(java.util.Map<java.lang.Object, java.lang.Object> m) throws Throwable {
        if (!(m.toString().equals("{}")))
            ;
        m.put("Harvey", m);
        if (!(m.toString().equals("{Harvey=(this Map)}")))
            ;
        m.clear();
        m.put(m, "Harvey");
        if (!(m.toString().equals("{(this Map)=Harvey}")))
            ;
        m.clear();
        m.hashCode();
    }
}

