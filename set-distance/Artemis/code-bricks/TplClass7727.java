import java.util.Random;
import java.util.LinkedList;
import java.util.Vector;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class TplClass7727 {

    private static final void method(java.util.Random rnd, int SIZE) throws Throwable {
        List[] a = { new ArrayList(), new LinkedList(), new Vector() };
        for (int i = 0; i < a.length; i++) {
            List lst = a[i];
            for (int j = 0; j < SIZE; j++) lst.add(new Integer(j));
            int totalDist = 0;
            for (int j = 0; j < 10000; j++) {
                int dist = rnd.nextInt(200) - 100;
                Collections.rotate(lst, dist);
                // Check that things are as they should be
                totalDist = (totalDist + dist) % SIZE;
                if (totalDist < 0)
                    totalDist += SIZE;
                int index = 0;
                for (int k = totalDist; k < SIZE; k++, index++) if (((Integer) lst.get(k)).intValue() != index)
                    ;
                for (int k = 0; k < totalDist; k++, index++) if (((Integer) lst.get(k)).intValue() != index)
                    ;
            }
        }
    }
}

