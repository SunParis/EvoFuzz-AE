import java.util.concurrent.CountDownLatch;

public class TplClass4116 {

    private static final void method(java.util.concurrent.CountDownLatch cdl) throws Throwable {
        try {
            cdl.await();
            Class<?> c0 = Class.forName("Main$BigClass");
        } catch (Exception e) {
        }
    }
}

