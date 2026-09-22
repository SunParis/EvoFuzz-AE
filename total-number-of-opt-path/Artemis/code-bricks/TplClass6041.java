import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class TplClass6041 {

    private static final void method() throws Throwable {
        Random rnd = new Random();
        List<Boolean> list = new ArrayList<Boolean>();
        int numFalse = 0;
        for (int i = 0; i < 1000; i++) {
            boolean element = rnd.nextBoolean();
            if (!element)
                numFalse++;
            // Autoboxing!
            list.add(element);
        }
        Collections.sort(list);
        for (int i = 0; i < numFalse; i++) if (// Autounboxing doesn't work yet!
        list.get(i).booleanValue())
            ;
        for (int i = numFalse; i < 1000; i++) if (// Autounboxing doesn't work yet!
        !list.get(i).booleanValue())
            ;
    }
}

