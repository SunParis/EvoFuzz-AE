import java.util.TreeSet;
import java.util.Set;
import java.util.SortedSet;

public class TplClass6222 {

    private static final void method() throws Throwable {
        SortedSet treeSet = new TreeSet();
        for (int i = 1; i <= 10; i++) treeSet.add(new Integer(i));
        Set subSet = treeSet.subSet(new Integer(4), new Integer(10));
        // Used to throw exception
        subSet.clear();
        int[] a = new int[] { 1, 2, 3, 10 };
        Set s = new TreeSet();
        for (int i = 0; i < a.length; i++) s.add(new Integer(a[i]));
        if (!treeSet.equals(s))
            ;
    }
}

