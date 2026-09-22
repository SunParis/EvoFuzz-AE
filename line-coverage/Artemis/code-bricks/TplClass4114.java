import java.util.concurrent.CountDownLatch;

public class TplClass4114 {

    private static final void method(java.util.concurrent.CountDownLatch cdl) throws Throwable {
        for (int i = 0; i < 10; ++i) {
            Runtime.getRuntime().gc();
            if (i == 0) {
                cdl.countDown();
            }
        }
    }
}

