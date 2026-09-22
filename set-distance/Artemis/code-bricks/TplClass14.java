import java.util.concurrent.atomic.AtomicReferenceArray;

public class TplClass14 {

    private static final void method(java.util.concurrent.atomic.AtomicReferenceArray<java.lang.Integer> x, int i, java.lang.Integer y) throws Throwable {
        x.getAndSet(i % x.length(), y);
    }
}

