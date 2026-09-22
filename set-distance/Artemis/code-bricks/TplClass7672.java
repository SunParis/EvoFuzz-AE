import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TplClass7672 {

    private static final void method(int x, int i, java.util.Random rnd, java.util.List[] lists) throws Throwable {
        int size = rnd.nextInt(10) + 2;
        List<Integer> list = new ArrayList<Integer>(size);
        for (int j = 1; j < size; j++) list.add(x++);
        list.add(x);
        Collections.shuffle(list);
        lists[i] = list;
    }
}

