import java.nio.channels.SelectionKey;

public class TplClass6656 {

    private static final void method(int failCount, java.nio.channels.SelectionKey key) throws Throwable {
        if (key.isWritable()) {
            failCount++;
            if (failCount > 10)
                ;
            Thread.sleep(250);
        }
    }
}

