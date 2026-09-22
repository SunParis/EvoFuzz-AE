import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass224 {

    private static final void method(int ARRLEN, java.util.concurrent.atomic.AtomicIntegerArray a, int OFFSET, java.util.concurrent.atomic.AtomicIntegerArray b) throws Throwable {
        for (int i = 0; i < ARRLEN - OFFSET; i += 1) {
            a.compareAndSet((i + OFFSET), 123, -123);
            b.compareAndSet((i + OFFSET), 123, -103);
        }
    }
}

