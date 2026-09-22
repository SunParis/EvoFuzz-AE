import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass221 {

    private static final void method(int ARRLEN, java.util.concurrent.atomic.AtomicIntegerArray a, int OFFSET, int old) throws Throwable {
        for (int i = 0; i < ARRLEN - OFFSET; i += 1) {
            a.compareAndSet((i + OFFSET), old, -123);
        }
    }
}

