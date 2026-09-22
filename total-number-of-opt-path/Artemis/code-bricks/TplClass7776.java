import java.util.Collections;
import java.util.List;

public class TplClass7776 {

    private static final void method(java.util.List<java.lang.Integer> list, int N) throws Throwable {
        for (int i = 0; i < N; i++) for (int j = 0; j < i; j++) list.add(i);
        Collections.shuffle(list);
        for (int i = 0; i < N; i++) if (Collections.frequency(list, i) != i)
            ;
    }
}

