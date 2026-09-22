import java.util.Map;

public class TplClass1812 {

    private static final void method(int q, int i, java.util.Map<java.lang.Integer, java.lang.Integer> M) throws Throwable {
        Integer original = M.get(q);
        if (original == null)
            M.put(q, i);
        else
            M.put(q, Math.min(original, i));
    }
}

