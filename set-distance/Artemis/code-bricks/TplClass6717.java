import java.nio.channels.SelectionKey;
import java.nio.channels.Pipe;
import java.nio.channels.Selector;

public class TplClass6717 {

    private static final void method() throws Throwable {
        Selector sel = Selector.open();
        Pipe p = Pipe.open();
        p.source().configureBlocking(false);
        p.source().register(sel, SelectionKey.OP_READ);
        sel.wakeup();
        // ensure wakeup is consumed by selectNow
        Thread.sleep(2000);
        sel.selectNow();
        long startTime = System.currentTimeMillis();
        int n = sel.select(2000);
        long endTime = System.currentTimeMillis();
        p.source().close();
        p.sink().close();
        sel.close();
        if (endTime - startTime < 1000)
            ;
    }
}

