import java.util.concurrent.CountDownLatch;

public class TplClass3888 {

    private static final void method(java.util.concurrent.CountDownLatch waitLatch, java.lang.Thread mJoinMe, java.util.concurrent.CountDownLatch progressLatch) throws Throwable {
        waitLatch.countDown();
        progressLatch.await();
        mJoinMe.join();
    }
}

