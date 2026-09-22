import java.util.concurrent.CountDownLatch;

public class TplClass2878 {

    private static final void method(int nativeDataPtr, java.util.concurrent.CountDownLatch freeSignal) throws Throwable {
        // Wake up the main thread that is [or will be] blocked until this native data is freed.
        freeSignal.countDown();
    }
}

