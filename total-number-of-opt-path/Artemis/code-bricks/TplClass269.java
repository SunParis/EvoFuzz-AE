import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass269 {

    private static final void method(java.util.concurrent.atomic.AtomicIntegerArray a, java.util.concurrent.atomic.AtomicIntegerArray b, int c, int d, int i, int k) throws Throwable {
        a.compareAndSet((i + k), -123, c);
        b.compareAndSet((i + k), -103, d);
    }
}

