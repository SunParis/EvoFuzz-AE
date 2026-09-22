import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass314 {

    private static final void method(int limit, java.util.concurrent.atomic.AtomicIntegerArray a, java.util.concurrent.atomic.AtomicIntegerArray b, int c, int d) throws Throwable {
        for (int i = limit; i >= 0; i -= 1) {
            a.lazySet(i, c);
            b.lazySet((limit - i), d);
        }
    }
}

