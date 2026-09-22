import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass214 {

    private static final void method(int ARRLEN, java.util.concurrent.atomic.AtomicIntegerArray a, java.util.concurrent.atomic.AtomicIntegerArray b) throws Throwable {
        for (int i = ARRLEN - 1; i >= 0; i -= 1) {
            a.compareAndSet(i, 123, -123);
            b.compareAndSet(i, 123, -103);
        }
    }
}

