import java.util.Iterator;

public class TplClass7558 {

    private static final void method(java.util.Iterator<java.lang.Integer> i) throws Throwable {
        if ((i.next().intValue() & 1) == 1)
            i.remove();
    }
}

