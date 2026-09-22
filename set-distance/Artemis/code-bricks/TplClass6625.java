import java.nio.channels.Selector;

public class TplClass6625 {

    private static final void method() throws Throwable {
        int waitTime = 4000;
        Selector selector = Selector.open();
        try {
            selector.wakeup();
            long t1 = System.currentTimeMillis();
            selector.select(waitTime);
            long t2 = System.currentTimeMillis();
            long totalTime = t2 - t1;
            if (totalTime > waitTime)
                ;
        } finally {
            selector.close();
        }
    }
}

