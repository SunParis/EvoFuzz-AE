import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass207 {

    private static final void method(int ARRLEN, java.util.concurrent.atomic.AtomicIntegerArray a, int b, int old) throws Throwable {
        for (int i = 0; i < ARRLEN; i += 1) {
            a.compareAndSet(i, old, b);
        }
    }
}

