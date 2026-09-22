import java.util.Vector;
import java.util.Collections;
import java.util.List;

public class TplClass7744 {

    private static final void method(int[] sizes, int i) throws Throwable {
        Vector v = new Vector();
        int size = sizes[i];
        for (int j = 0; j < size; j++) v.add(new Integer(j));
        List l = Collections.list(v.elements());
        if (!l.equals(v))
            ;
    }
}

