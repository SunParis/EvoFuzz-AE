import java.util.Iterator;

public class TplClass7682 {

    private static final void method(java.util.Iterator it) throws Throwable {
        while (it.hasNext()) if (((Integer) it.next()).intValue() % 2 == 1)
            it.remove();
    }
}

