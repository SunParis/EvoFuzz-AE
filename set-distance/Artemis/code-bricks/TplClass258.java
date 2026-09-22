import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass258 {

    private static final void method(int ARRLEN, java.util.concurrent.atomic.AtomicIntegerArray a, java.util.concurrent.atomic.AtomicIntegerArray b, int c, int d) throws Throwable {
        int limit = ARRLEN - 1;
        for (int i = limit; i >= 0; i -= 1) {
            a.compareAndSet(i, -123, c);
            b.compareAndSet((limit - i), -103, d);
        }
    }
}

