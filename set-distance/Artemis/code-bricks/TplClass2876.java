import java.util.concurrent.CountDownLatch;

public class TplClass2876 {

    private static final void method(java.util.concurrent.CountDownLatch mFreeSignalB) throws Throwable {
        // Block until dataB is definitely freed.
        mFreeSignalB.await();
    }
}

