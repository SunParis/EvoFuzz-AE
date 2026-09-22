import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class TplClass7667 {

    private static final void method(int i, java.util.List[] lists, int N) throws Throwable {
        for (int j = 0; j < N; j++) {
            boolean disjoint = (Math.abs(i - j) > 1);
            List<Integer> a = (List<Integer>) lists[i];
            List<Integer> b = (List<Integer>) lists[j];
            if (Collections.disjoint(a, b) != disjoint)
                ;
            if (Collections.disjoint(new HashSet<Integer>(a), b) != disjoint)
                ;
            if (Collections.disjoint(new HashSet<Integer>(a), new HashSet<Integer>(b)) != disjoint)
                ;
        }
    }
}

