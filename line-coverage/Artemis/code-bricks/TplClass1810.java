import java.util.Map;

public class TplClass1810 {

    private static final void method(int q, int i, java.lang.Integer original, java.util.Map<java.lang.Integer, java.lang.Integer> M) throws Throwable {
        if (original == null)
            M.put(q, i);
        else
            M.put(q, Math.min(original, i));
    }
}

