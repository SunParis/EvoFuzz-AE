import java.util.Iterator;
import java.util.HashSet;
import java.util.HashMap;

public class TplClass7588 {

    private static final void method(int elemBeforePut, java.util.HashMap<java.lang.Integer, java.lang.Integer> hm) throws Throwable {
        if (elemBeforePut > hm.size()) {
        }
        // Create a copy of the keys
        HashSet<Integer> keys = new HashSet<>(hm.size());
        keys.addAll(hm.keySet());
        HashSet<Integer> collected = new HashSet<>(hm.size());
        // Run itr for elemBeforePut items, collecting returned elems
        Iterator<Integer> itr = hm.keySet().iterator();
        for (int i = 0; i < elemBeforePut; i++) {
            Integer retVal = itr.next();
            if (!collected.add(retVal)) {
            }
        }
        // Do put() to replace entry (and resize table when bug present)
        if (null == hm.put(0, 100)) {
        }
        // Finish itr + collecting returned elems
        while (itr.hasNext()) {
            Integer retVal = itr.next();
            if (!collected.add(retVal)) {
            }
        }
        // Compare returned elems to original copy of keys
        if (!keys.equals(collected)) {
        }
    }
}

