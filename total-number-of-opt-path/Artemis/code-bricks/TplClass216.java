import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass216 {

    private static final void method(int ARRLEN, int limit, java.util.concurrent.atomic.AtomicIntegerArray a, int old) throws Throwable {
        for (int i = 0; i < ARRLEN; i += 1) {
            a.compareAndSet((limit - i), old, -123);
        }
    }
}

