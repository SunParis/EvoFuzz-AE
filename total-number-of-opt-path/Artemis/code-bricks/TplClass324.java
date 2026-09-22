import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass324 {

    private static final void method(int ARRLEN, java.util.concurrent.atomic.AtomicIntegerArray a, java.util.concurrent.atomic.AtomicIntegerArray b, int c, int d, int k) throws Throwable {
        for (int i = 0; i < ARRLEN - k; i += 1) {
            a.lazySet((i + k), c);
            b.lazySet((i + k), d);
        }
    }
}

