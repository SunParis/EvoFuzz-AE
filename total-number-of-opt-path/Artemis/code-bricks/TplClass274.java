import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass274 {

    private static final void method(java.util.concurrent.atomic.AtomicIntegerArray a, java.util.concurrent.atomic.AtomicIntegerArray b, int c, int d, int i, int SCALE) throws Throwable {
        a.compareAndSet((i * SCALE), -123, c);
        b.compareAndSet((i * SCALE), -103, d);
    }
}

