import java.nio.channels.SelectionKey;
import java.util.Iterator;

public class TplClass6653 {

    private static final void method(java.util.Iterator<java.nio.channels.SelectionKey> iterator, int failCount, boolean done, java.nio.channels.SelectionKey key) throws Throwable {
        while (iterator.hasNext()) {
            key = iterator.next();
            iterator.remove();
            if (key.isWritable()) {
                failCount++;
                if (failCount > 10)
                    ;
                Thread.sleep(250);
            }
            if (key.isReadable()) {
                done = true;
            }
        }
    }
}

