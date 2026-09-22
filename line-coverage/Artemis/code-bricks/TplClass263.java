import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass263 {

    private static final void method(java.util.concurrent.atomic.AtomicIntegerArray a, int i, int OFFSET, java.util.concurrent.atomic.AtomicIntegerArray b) throws Throwable {
        a.compareAndSet((i + OFFSET), 123, -123);
        b.compareAndSet((i + OFFSET), 123, -103);
    }
}

