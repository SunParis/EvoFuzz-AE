import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass257 {

    private static final void method(int limit, java.util.concurrent.atomic.AtomicIntegerArray a, int i, java.util.concurrent.atomic.AtomicIntegerArray b) throws Throwable {
        a.compareAndSet((limit - i), 123, -123);
        b.compareAndSet(i, 123, -103);
    }
}

