import java.util.concurrent.atomic.AtomicInteger;

public class TplClass3606 {

    private static final void method(java.util.concurrent.atomic.AtomicInteger[] targetted, int i, java.util.concurrent.atomic.AtomicInteger[] called) throws Throwable {
        called[i] = new AtomicInteger(0);
        targetted[i] = new AtomicInteger(0);
    }
}

