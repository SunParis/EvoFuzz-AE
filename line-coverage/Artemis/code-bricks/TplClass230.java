import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass230 {

    private static final void method(int ARRLEN, java.util.concurrent.atomic.AtomicIntegerArray a, java.util.concurrent.atomic.AtomicIntegerArray b, int c, int d, int k) throws Throwable {
        for (int i = 0; i < ARRLEN - k; i += 1) {
            a.compareAndSet((i + k), -123, c);
            b.compareAndSet((i + k), -103, d);
        }
    }
}

