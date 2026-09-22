import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass231 {

    private static final void method(int ARRLEN, java.util.concurrent.atomic.AtomicIntegerArray a, int SCALE, int old) throws Throwable {
        for (int i = 0; i * SCALE < ARRLEN; i += 1) {
            a.compareAndSet((i * SCALE), old, -123);
        }
    }
}

