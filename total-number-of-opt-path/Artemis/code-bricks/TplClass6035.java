import java.util.Random;
import java.util.List;

public class TplClass6035 {

    private static final void method(java.util.Random rnd, java.util.List<java.lang.Boolean> list, int numFalse) throws Throwable {
        for (int i = 0; i < 1000; i++) {
            boolean element = rnd.nextBoolean();
            if (!element)
                numFalse++;
            // Autoboxing!
            list.add(element);
        }
    }
}

