import java.util.Random;
import java.util.Collections;
import java.util.List;

public class TplClass7729 {

    private static final void method(java.util.Random rnd, int j, int totalDist, java.util.List lst, int SIZE) throws Throwable {
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

