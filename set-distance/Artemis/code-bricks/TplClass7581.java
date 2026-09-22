import java.util.Iterator;
import java.util.HashSet;

public class TplClass7581 {

    private static final void method(int elemBeforePut, java.util.HashSet<java.lang.Integer> collected, java.util.Iterator<java.lang.Integer> itr) throws Throwable {
        for (int i = 0; i < elemBeforePut; i++) {
            Integer retVal = itr.next();
            if (!collected.add(retVal)) {
            }
        }
    }
}

