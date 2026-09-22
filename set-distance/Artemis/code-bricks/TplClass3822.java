import java.util.concurrent.CountDownLatch;

public class TplClass3822 {

    private static final void method(java.util.concurrent.CountDownLatch first, java.util.concurrent.CountDownLatch second) throws Throwable {
        first.countDown();
        second.await();
    }
}

