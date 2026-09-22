import java.util.concurrent.CountDownLatch;

public class TplClass3887 {

    private static final void method(java.util.concurrent.CountDownLatch waitLatch, java.lang.Thread mJoinMe, java.util.concurrent.CountDownLatch progressLatch) throws Throwable {
        try {
            waitLatch.countDown();
            progressLatch.await();
            mJoinMe.join();
        } catch (InterruptedException ie) {
        } finally {
        }
    }
}

