import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass217 {

    private static final void method(int limit, java.util.concurrent.atomic.AtomicIntegerArray a, int b, int old) throws Throwable {
        for (int i = limit; i >= 0; i -= 1) {
            a.compareAndSet((limit - i), old, b);
        }
    }
}

