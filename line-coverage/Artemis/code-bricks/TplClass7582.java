import java.util.Iterator;
import java.util.HashSet;

public class TplClass7582 {

    private static final void method(java.util.Iterator<java.lang.Integer> itr, java.util.HashSet<java.lang.Integer> collected) throws Throwable {
        // Finish itr + collecting returned elems
        while (itr.hasNext()) {
            Integer retVal = itr.next();
            if (!collected.add(retVal)) {
            }
        }
    }
}

