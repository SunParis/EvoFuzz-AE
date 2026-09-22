import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass319 {

    private static final void method(int ARRLEN, java.util.concurrent.atomic.AtomicIntegerArray a, java.util.concurrent.atomic.AtomicIntegerArray b, int c, int d, int OFFSET) throws Throwable {
        for (int i = 0; i < ARRLEN - OFFSET; i += 1) {
            a.lazySet((i + OFFSET), c);
            b.lazySet((i + OFFSET), d);
        }
    }
}

