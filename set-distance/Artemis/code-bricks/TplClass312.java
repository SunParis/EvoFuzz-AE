import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass312 {

    private static final void method(int ARRLEN, int limit, java.util.concurrent.atomic.AtomicIntegerArray a, java.util.concurrent.atomic.AtomicIntegerArray b) throws Throwable {
        for (int i = 0; i < ARRLEN; i += 1) {
            a.lazySet(i, b.get(limit - i));
        }
    }
}

