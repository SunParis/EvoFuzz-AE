import java.util.Vector;
import java.util.Collections;
import java.util.List;

public class TplClass7740 {

    private static final void method(int[] sizes) throws Throwable {
        for (int i = 0; i < sizes.length; i++) {
            Vector v = new Vector();
            int size = sizes[i];
            for (int j = 0; j < size; j++) v.add(new Integer(j));
            List l = Collections.list(v.elements());
            if (!l.equals(v))
                ;
        }
    }
}

