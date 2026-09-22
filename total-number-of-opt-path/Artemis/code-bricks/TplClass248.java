import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass248 {

    private static final void method(java.util.concurrent.atomic.AtomicIntegerArray a, int i, java.util.concurrent.atomic.AtomicIntegerArray b, int c, int d) throws Throwable {
        a.compareAndSet(i, -123, c);
        b.compareAndSet(i, -103, d);
    }
}

