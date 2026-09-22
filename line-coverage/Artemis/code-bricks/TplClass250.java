import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass250 {

    private static final void method(int ARRLEN, java.util.concurrent.atomic.AtomicIntegerArray a, int old) throws Throwable {
        int limit = ARRLEN - 1;
        for (int i = 0; i < ARRLEN; i += 1) {
            a.compareAndSet((limit - i), old, -123);
        }
    }
}

