import java.util.concurrent.CountDownLatch;

public class TplClass4117 {

    private static final void method(int i, java.util.concurrent.CountDownLatch cdl) throws Throwable {
        Runtime.getRuntime().gc();
        if (i == 0) {
            cdl.countDown();
        }
    }
}

