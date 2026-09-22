import java.util.Iterator;

public class TplClass6360 {

    private static final void method(java.lang.Object x1, java.lang.Object x2, java.util.Iterator it) throws Throwable {
        Object x = it.next();
        if (x == x1 || x == x2)
            it.remove();
    }
}

