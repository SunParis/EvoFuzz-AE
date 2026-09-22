import java.nio.channels.Selector;

public class TplClass6624 {

    private static final void method(int waitTime, java.nio.channels.Selector selector) throws Throwable {
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

