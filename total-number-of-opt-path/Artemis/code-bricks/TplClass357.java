import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass357 {

    private static final void method(int ARRLEN, java.util.concurrent.atomic.AtomicIntegerArray a, java.util.concurrent.atomic.AtomicIntegerArray b, int c, int d) throws Throwable {
        int limit = ARRLEN - 1;
        for (int i = limit; i >= 0; i -= 1) {
            a.lazySet(i, c);
            b.lazySet((limit - i), d);
        }
    }
}

