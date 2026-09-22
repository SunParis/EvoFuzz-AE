import java.util.concurrent.CopyOnWriteArrayList;
import java.util.LinkedList;

public class TplClass1042 {

    private static final void method() throws Throwable {
        try {
            CopyOnWriteArrayList c = new CopyOnWriteArrayList();
            // should throw IndexOutOfBoundsException
            c.addAll(-1, new LinkedList());
        } catch (IndexOutOfBoundsException e) {
        }
    }
}

