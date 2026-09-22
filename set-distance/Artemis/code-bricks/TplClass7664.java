import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TplClass7664 {

    private static final void method(int x, java.util.Random rnd, java.util.List[] lists, int N) throws Throwable {
        for (int i = 0; i < N; i++) {
            int size = rnd.nextInt(10) + 2;
            List<Integer> list = new ArrayList<Integer>(size);
            for (int j = 1; j < size; j++) list.add(x++);
            list.add(x);
            Collections.shuffle(list);
            lists[i] = list;
        }
    }
}

