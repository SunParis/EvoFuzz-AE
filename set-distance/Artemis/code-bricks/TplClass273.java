import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass273 {

    private static final void method(java.util.concurrent.atomic.AtomicIntegerArray a, int i, java.util.concurrent.atomic.AtomicIntegerArray b, int SCALE) throws Throwable {
        a.compareAndSet((i * SCALE), 123, -123);
        b.compareAndSet((i * SCALE), 123, -103);
    }
}

