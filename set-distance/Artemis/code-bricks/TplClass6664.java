import java.nio.channels.SelectionKey;
import java.util.Set;
import java.util.Iterator;
import java.nio.channels.Selector;

public class TplClass6664 {

    private static final void method(java.nio.channels.Selector selector, int failCount, boolean done, java.nio.channels.SelectionKey key) throws Throwable {
        int nSelected = selector.select();
        if (nSelected > 0) {
            if (nSelected > 1)
                ;
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();
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
}

