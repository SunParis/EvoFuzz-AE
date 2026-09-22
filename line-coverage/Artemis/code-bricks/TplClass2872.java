import java.util.concurrent.CountDownLatch;

public class TplClass2872 {

    private static final void method(java.util.concurrent.CountDownLatch mFreeSignalA) throws Throwable {
        try {
            // Block until dataA is definitely freed.
            mFreeSignalA.await();
        } catch (InterruptedException e) {
        }
    }
}

