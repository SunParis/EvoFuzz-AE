import java.util.concurrent.atomic.AtomicIntegerArray;

public class TplClass311 {

    private static final void method(int limit, java.util.concurrent.atomic.AtomicIntegerArray a, int b) throws Throwable {
        for (int i = limit; i >= 0; i -= 1) {
            a.lazySet((limit - i), b);
        }
    }
}

