import java.util.concurrent.CountDownLatch;

public class TplClass4755 {

    private static final void method(java.util.concurrent.CountDownLatch busyLatch, java.util.concurrent.CountDownLatch proceedLatch) throws Throwable {
        busyLatch.countDown();
        proceedLatch.await();
    }
}

