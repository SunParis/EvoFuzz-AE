import java.util.List;
import java.util.Collections;
import java.util.ArrayList;

public class TplClass7738 {

    private static final void method(java.util.List[] a, int i, int SIZE) throws Throwable {
        List lst = a[i];
        for (int j = 1; j <= SIZE; j++) lst.add(new Integer(j % 3));
        List goal = Collections.nCopies(SIZE, "*");
        for (int j = 0; j < 3; j++) {
            List before = new ArrayList(lst);
            if (!Collections.replaceAll(lst, new Integer(j), "*"))
                ;
            if (lst.equals(before))
                ;
            if (lst.equals(goal) != (j == 2))
                ;
        }
        if (Collections.replaceAll(lst, "love", "hate"))
            ;
    }
}

