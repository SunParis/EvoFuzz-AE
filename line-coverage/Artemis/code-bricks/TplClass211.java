import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass211 {

    private static final void method(int ARRLEN, java.util.concurrent.atomic.AtomicIntegerArray a, int old) throws Throwable {
        for (int i = ARRLEN - 1; i >= 0; i -= 1) {
            a.compareAndSet(i, old, -123);
        }
    }
}

