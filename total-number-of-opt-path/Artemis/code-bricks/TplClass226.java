import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass226 {

    private static final void method(int ARRLEN, java.util.concurrent.atomic.AtomicIntegerArray a, int k, int old) throws Throwable {
        for (int i = 0; i < ARRLEN - k; i += 1) {
            a.compareAndSet((i + k), old, -123);
        }
    }
}

