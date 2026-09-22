import java.util.List;
import java.util.Random;

public class TplClass6042 {

    private static final void method(java.util.Random rnd, java.util.List<java.lang.Boolean> list, int numFalse) throws Throwable {
        boolean element = rnd.nextBoolean();
        if (!element)
            numFalse++;
        // Autoboxing!
        list.add(element);
    }
}

