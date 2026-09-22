import java.util.Iterator;

public class TplClass6356 {

    private static final void method(java.lang.Object x1, java.lang.Object x2, java.util.Iterator it) throws Throwable {
        while (it.hasNext()) {
            Object x = it.next();
            if (x == x1 || x == x2)
                it.remove();
        }
    }
}

