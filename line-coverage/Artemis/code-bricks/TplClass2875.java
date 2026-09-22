import java.util.concurrent.CountDownLatch;

public class TplClass2875 {

    private static final void method(java.util.concurrent.CountDownLatch mFreeSignalA) throws Throwable {
        // Block until dataA is definitely freed.
        mFreeSignalA.await();
    }
}

