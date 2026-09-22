import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass210 {

    private static final void method(int ARRLEN, java.util.concurrent.atomic.AtomicIntegerArray a, java.util.concurrent.atomic.AtomicIntegerArray b, int c, int d) throws Throwable {
        for (int i = 0; i < ARRLEN; i += 1) {
            a.compareAndSet(i, -123, c);
            b.compareAndSet(i, -103, d);
        }
    }
}

