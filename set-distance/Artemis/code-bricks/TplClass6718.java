import java.nio.channels.Pipe;
import java.nio.channels.Selector;

public class TplClass6718 {

    private static final void method() throws Throwable {
        Selector sel = Selector.open();
        Pipe p = Pipe.open();
        p.source().configureBlocking(false);
        sel.wakeup();
        // ensure wakeup is consumed by selectNow
        Thread.sleep(2000);
        sel.selectNow();
        long startTime = System.currentTimeMillis();
        int n = sel.select(2000);
        long endTime = System.currentTimeMillis();
        sel.close();
        if (endTime - startTime < 1000)
            ;
    }
}

