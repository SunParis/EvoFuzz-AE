import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass219 {

    private static final void method(int ARRLEN, int limit, java.util.concurrent.atomic.AtomicIntegerArray a, java.util.concurrent.atomic.AtomicIntegerArray b) throws Throwable {
        for (int i = 0; i < ARRLEN; i += 1) {
            a.compareAndSet((limit - i), 123, -123);
            b.compareAndSet(i, 123, -103);
        }
    }
}

