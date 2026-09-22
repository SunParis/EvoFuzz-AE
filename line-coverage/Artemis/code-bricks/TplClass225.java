import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass225 {

    private static final void method(int ARRLEN, java.util.concurrent.atomic.AtomicIntegerArray a, java.util.concurrent.atomic.AtomicIntegerArray b, int c, int d, int OFFSET) throws Throwable {
        for (int i = 0; i < ARRLEN - OFFSET; i += 1) {
            a.compareAndSet((i + OFFSET), -123, c);
            b.compareAndSet((i + OFFSET), -103, d);
        }
    }
}

