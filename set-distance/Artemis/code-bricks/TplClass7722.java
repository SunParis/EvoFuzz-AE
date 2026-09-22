import java.util.Random;
import java.util.Collections;
import java.util.List;

public class TplClass7722 {

    private static final void method(java.util.Random rnd, int totalDist, java.util.List lst, int SIZE) throws Throwable {
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

