import java.util.concurrent.CountDownLatch;

public class TplClass2873 {

    private static final void method(java.util.concurrent.CountDownLatch mFreeSignalB) throws Throwable {
        try {
            // Block until dataB is definitely freed.
            mFreeSignalB.await();
        } catch (InterruptedException e) {
        }
    }
}

