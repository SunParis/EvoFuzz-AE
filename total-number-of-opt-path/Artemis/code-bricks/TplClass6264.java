import java.util.Collection;
import java.util.concurrent.LinkedBlockingQueue;

public class TplClass6264 {

    private static final void method() throws Throwable {
        Collection<Integer> c = new LinkedBlockingQueue<Integer>();
        if (c.toArray(new Integer[] { 42 })[0] != null)
            ;
    }
}

