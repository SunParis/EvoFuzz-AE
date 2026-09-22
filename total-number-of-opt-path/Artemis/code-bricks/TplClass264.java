import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass264 {

    private static final void method(java.util.concurrent.atomic.AtomicIntegerArray a, java.util.concurrent.atomic.AtomicIntegerArray b, int c, int d, int i, int OFFSET) throws Throwable {
        a.compareAndSet((i + OFFSET), -123, c);
        b.compareAndSet((i + OFFSET), -103, d);
    }
}

