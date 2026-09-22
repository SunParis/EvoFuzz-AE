import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

public class TplClass5686 {

    private static final void method(java.lang.String msg, java.util.concurrent.atomic.AtomicBoolean failed, java.util.concurrent.CountDownLatch done) throws Throwable {
        failed.set(true);
        done.countDown();
    }
}

