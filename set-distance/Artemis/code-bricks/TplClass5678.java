import java.nio.channels.AsynchronousCloseException;
import java.util.concurrent.CountDownLatch;

public class TplClass5678 {

    private static final void method(java.lang.Throwable exc, java.util.concurrent.CountDownLatch latch) throws Throwable {
        if (!(exc instanceof AsynchronousCloseException))
            ;
        latch.countDown();
    }
}

