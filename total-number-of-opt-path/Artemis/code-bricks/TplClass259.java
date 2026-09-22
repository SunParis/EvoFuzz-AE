import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass259 {

    private static final void method(java.util.concurrent.atomic.AtomicIntegerArray a, java.util.concurrent.atomic.AtomicIntegerArray b, int c, int d, int limit, int i) throws Throwable {
        a.compareAndSet(i, -123, c);
        b.compareAndSet((limit - i), -103, d);
    }
}

