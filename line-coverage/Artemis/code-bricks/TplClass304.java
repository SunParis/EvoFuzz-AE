import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass304 {

    private static final void method(int ARRLEN, java.util.concurrent.atomic.AtomicIntegerArray a, java.util.concurrent.atomic.AtomicIntegerArray b, int c, int d) throws Throwable {
        for (int i = 0; i < ARRLEN; i += 1) {
            a.lazySet(i, c);
            b.lazySet(i, d);
        }
    }
}

