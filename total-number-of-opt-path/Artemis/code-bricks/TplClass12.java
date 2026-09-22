import java.util.concurrent.atomic.AtomicReferenceArray;

public class TplClass12 {

    private static final void method(java.util.concurrent.atomic.AtomicReferenceArray<java.lang.Integer> x, java.lang.Integer y) throws Throwable {
        for (int i = 0; i < 50000; i++) {
            x.getAndSet(i % x.length(), y);
        }
    }
}

