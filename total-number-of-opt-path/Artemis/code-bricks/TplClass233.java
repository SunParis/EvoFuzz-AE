import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass233 {

    private static final void method(int ARRLEN, java.util.concurrent.atomic.AtomicIntegerArray a, java.util.concurrent.atomic.AtomicIntegerArray b, int SCALE) throws Throwable {
        for (int i = 0; i * SCALE < ARRLEN; i += 1) {
            a.compareAndSet((i * SCALE), -123, b.get(i * SCALE));
        }
    }
}

